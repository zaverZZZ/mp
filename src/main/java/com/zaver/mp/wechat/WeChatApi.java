package com.zaver.mp.wechat;

import com.zaver.mp.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName : WeChatApi
 * @Description TODO
 * @Date : 2019/8/21 1:58
 * @Author ABC
 * @Version 1.0
 * @Explanation ：
 */
@Controller
@RequestMapping("/wechatApi")
public class WeChatApi {
    private static final Logger log = LoggerFactory.getLogger(WeChatApi.class);
    @ResponseBody
    @RequestMapping("/index")
    public Object index(String signature,String echostr,String timestamp,String nonce,
                        HttpServletRequest request, HttpServletResponse response){

        PrintWriter out = null;
        try {
            //设置编码
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            out = response.getWriter();
            //微信接入验证
            if(StringUtils.isNotEmpty(echostr)){
                Map<String,String> param = new HashMap<>();
                param.put("signature",signature);
                param.put("timestamp",timestamp);
                param.put("nonce",nonce);
                param.put("echostr",echostr);
                response.setContentType("content-type:text");
                String s = WxUtils.validToken(param);
                out.write(StringUtils.isEmpty(s)?"":s);
            }else{
                //微信事件监听
                //this.handleMsg(request.getInputStream(), out);
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        } finally {
            if( out != null) out.close();
        }
        return null;
    }
}
