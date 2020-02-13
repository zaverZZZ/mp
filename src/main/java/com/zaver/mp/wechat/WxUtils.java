package com.zaver.mp.wechat;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zaver.mp.utils.HttpUtil;
import com.zaver.mp.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName : WxUtils
 * @Description TODO
 * @Date : 2019/7/23 23:47
 * @Author ABC
 * @Version 1.0
 * @Explanation ：
 */
public class WxUtils {

    private static final Logger log = LoggerFactory.getLogger(WxUtils.class);

    // 注意线程安全
    private static List<AccessToken> tokenList = new ArrayList<>();
    private static Map<String, ConcurrentHashMap<String, WxJsConfig>> jsSignatureMap = new ConcurrentHashMap<>();//缓存pair<js签名,js签名过期时间>
    private static Map<String, Map<String, Long>> jsTicketMap = new ConcurrentHashMap<>();//    缓存pair<jsticket,jsTicket过期时间>

    /**
     * 获取jsconfig
     *
     * @param url
     * @return
     */
    public static WxJsConfig getJsConfig(String url) {

        //从缓存中获取
        ConcurrentHashMap<String, WxJsConfig> urlMap = null;
        WxJsConfig wxJsConfig = null;
        if (jsSignatureMap.containsKey(WeChatConfig.appid)) {
            urlMap = jsSignatureMap.get(WeChatConfig.appid);
            wxJsConfig = urlMap.get(url);
        }
        if (wxJsConfig != null && System.currentTimeMillis() < wxJsConfig.getExpireTime()) {
            //输出结果
            return wxJsConfig;
            //直接计算并缓存下来
        } else {
            if (urlMap == null) {
                urlMap = new ConcurrentHashMap<>();
            }
            //定义变量
            String signature = null;
            long timestamp = System.currentTimeMillis() / 1000;
            String noncestr = StringUtils.getIntegerString(20);
            //签名验证
            String resp = getJsTicket();
            if (resp == null || resp.isEmpty()) {
                return null;
            }
            String jsapi_ticket = resp;
            String[] arr = new String[]{"noncestr=" + noncestr, "jsapi_ticket=" + jsapi_ticket, "timestamp=" + timestamp, "url=" + url};
            Arrays.sort(arr);
            String str = arr[0] + "&" + arr[1] + "&" + arr[2] + "&" + arr[3];
            signature = SHA1(str).toLowerCase();
            int expires_in = 7200;//过期时间7200秒
            //缓存js签名
            wxJsConfig = new WxJsConfig();
            wxJsConfig.setNoncestr(noncestr);
            wxJsConfig.setSignature(signature);
            wxJsConfig.setTimestamp(timestamp);
            wxJsConfig.setExpireTime(System.currentTimeMillis() + (expires_in - 2) * 1000); //减一点时间，为了在极端情况下考虑程序执行时间
            urlMap.put(url, wxJsConfig);
            jsSignatureMap.put(WeChatConfig.appid, urlMap);
            //输出结果
            return wxJsConfig;
        }

    }

    /**
     * 获取jsticket
     *
     * @return
     */
    private static String getJsTicket() {
        //获取jsTicket url
        //>>获取token
        String access_token = getWxToken();
        String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + access_token + "&type=jsapi";
        //从缓存中获取
        String ticket = null;
        long ticketExpireTime = 0;
        if (jsTicketMap.containsKey(WeChatConfig.appid)) {
            Map<String, Long> pair = jsTicketMap.get(WeChatConfig.appid);
            for (String tic : pair.keySet()) {
                ticket = tic;
                ticketExpireTime = pair.get(tic);
            }
        }

        // TODO
        if (StringUtils.isNotEmpty(ticket) && System.currentTimeMillis() < ticketExpireTime) {
            //输出结果
            return ticket;
            //从微信获取并缓存下来
        } else {
            //请求并封装结果
            String result = HttpUtil.get(url);
            JSONObject obj = JSON.parseObject(result);
            if (!obj.containsKey("ticket")) {
                return null;
            }
            ticket = obj.get("ticket").toString();
            int expires_in = Integer.valueOf(obj.get("expires_in").toString());
            //缓存ticket
            ticketExpireTime = System.currentTimeMillis() + (expires_in - 5) * 1000; //-5代表从微信中获取有时间延迟
            jsTicketMap.clear();
            Map<String, Long> map = new HashMap<>();
            map.put(ticket, ticketExpireTime);
            jsTicketMap.put(WeChatConfig.appid, map);
            //输出结果
            return ticket;
        }
    }

    /**
     * 获取wxtoken
     *
     * @return
     */
    public static String getWxToken() {
        String tokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + WeChatConfig.appid + "&secret=" + WeChatConfig.appsecret;
        Long createTime = new Date().getTime();
        String token = "";
        System.out.println("tokenList : " + JSON.toJSONString(tokenList));
        if (tokenList != null && tokenList.size() != 0) {
            Long tokenTime = tokenList.get(tokenList.size() - 1).getCreateTime();
            Integer expIn = tokenList.get(tokenList.size() - 1).getExpires_in();
            if (((createTime - tokenTime) / 1000) >= expIn - 100) {                  //  预留出100秒余量  以免因为延迟或者软件运行造成时间不准确
                token = saveAccessToken(tokenList, tokenUrl, createTime);
            } else {
                token = tokenList.get(tokenList.size() - 1).getAccess_token();
            }
        } else {
            token = saveAccessToken(tokenList, tokenUrl, createTime);
        }
        System.out.println("tokenList : " + JSON.toJSONString(tokenList));
        return token;
    }

    /**
     * 保存替换过期的access_token
     *
     * @param tokenUrl   获取token的url
     * @param createTime 创建时间
     * @return
     */
    public static String saveAccessToken(List<AccessToken> list, String tokenUrl, Long createTime) {
        String token = "";
        String ACCESS_TOKEN_JSON = HttpUtil.get(tokenUrl);
        token = JSON.parseObject(ACCESS_TOKEN_JSON).getString("access_token");
        Integer expires_in = JSON.parseObject(ACCESS_TOKEN_JSON).getInteger("expires_in");
        AccessToken accessToken = new AccessToken();
        accessToken.setAccess_token(token);
        accessToken.setExpires_in(expires_in);
        accessToken.setCreateTime(createTime);
        list.clear();
        list.add(accessToken);
        return token;
    }

    public static String validToken(Map<String,String> param){

        //>>获取签名验证参数
        String signature = param.get("signature");
        String timestamp = param.get("timestamp");
        String nonce = param.get("nonce");
        //>>签名验证
        if(WxUtils.checkSignature(signature,timestamp,nonce)){
            return param.get("echostr");
        }else return null;
    }
    public static Boolean checkSignature(String signature, String timestamp, String nonce){
        //签名验证
        String[] arr = new String[]{WeChatConfig.token,timestamp,nonce};
        Arrays.sort(arr);
        String str = arr[0]+arr[1]+arr[2];
        System.out.println(str);
        str = SHA1(str).toLowerCase();
        System.out.println(str + "    " + signature );
        if(str.equals(signature)){
            return true;
        }else{
            return false;
        }
    }

    // sha1加密算法
    // 出处: https://www.open-open.com/lib/view/open1392185662160.html
    public static String SHA1(String decript) {
        try {
            MessageDigest digest = java.security.MessageDigest.getInstance("SHA-1");
            digest.update(decript.getBytes());
            byte messageDigest[] = digest.digest();
            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            // 字节数组转换为 十六进制 数
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
