package com.zaver.mp.modules.app.controller;

import com.zaver.mp.modules.app.model.User;
import com.zaver.mp.modules.app.service.UserService;
import com.zaver.mp.utils.Result;
import com.zaver.mp.utils.annotation.ApiIdempotent;
import com.zaver.mp.utils.exception.LocalException;
import com.zaver.mp.utils.redis.RedisUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName : RedirectController
 * @Description TODO
 * @Date : 2020/1/19 11:02
 * @Author ABC
 * @Version 1.0
 * @Explanation ：
 */
@RestController
@RequestMapping("/app/test")
public class TestController {

    @Autowired
    private UserService userService;

    // 测试幂等性
    @ApiIdempotent
    @GetMapping("/test1")
    public Result test1(){
        return Result.ok();
    }

    // 测试身份拦截
    @RequiresRoles("admin")
    @GetMapping("/test2")
    public Result test2(){
        return Result.ok();
    }

    // 测试事务
    @GetMapping("/test3")
    @Transactional(rollbackFor = LocalException.class)
    public Result test3(String s) throws Exception {
        User user = new User();
        user.setPassWord("1111");
        user.setNickName("1111");
        user.setCreateTime(111L);
        user.setUserName("1111");
        boolean save = userService.save(user);
        if("11".equals(s))throw new LocalException(Result.fail());
        if (s!=null)throw new Exception();
        /*PlatformTransactionManager ptm = new DataSourceTransactionManager();
        TransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus status = ptm.getTransaction(def);
        // do something
        ptm.commit(status);
        ptm.rollback(status);*/
        return Result.ok(save);
    }

    @GetMapping("/test4")
    public Result test4(){
        Map<String,Object> map = new HashMap<>();
        map.put("vvvvv",123);
        Result<Map<String,Object>> result = Result.getResult(map);
        result.put("asdasd","asdasd");
        return result;
    }

    @GetMapping("/test5")
    public Result test5() throws InterruptedException {
        Thread.sleep(30000L);
        User user = new User();
        user.setPassWord("sassss");
        Result<User> result = Result.getResult(user);
        result.put("aaaaaaa","aaaaaaaaa");
        result.put("bbbbbbb",11);
        User user1 = new User();
        user1.setPassWord("ggggggggggg");
        result.put("cccccc",user1);
        return result;
    }

    @Autowired
    private RedisUtils redisUtils;
    @GetMapping("/saveRedis")
    public Result saveRedis(String key,String value){
        redisUtils.setStr(key,value);
        return Result.ok();
    }

    @GetMapping("/deleteRedis")
    public Result deleteRedis(String key){
        Boolean delete = redisUtils.delete(key);
        return Result.ok(delete);
    }

    @GetMapping("/testRequest")
    public Result testRequest(HttpServletRequest request){
        return Result.ok();
    }
}
