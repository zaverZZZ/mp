package com.zaver.mp.app.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zaver.mp.app.model.User;
import com.zaver.mp.app.service.UserService;
import com.zaver.mp.utils.BeanUtil;
import com.zaver.mp.utils.Result;
import com.zaver.mp.utils.exception.LocalException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * @ClassName : UserController
 * @Description TODO
 * @Date : 2019/4/5 17:18
 * @Author ABC
 * @Version 1.0
 * @Explanation ：
 */
@RestController()
@RequestMapping("/user")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private StringRedisTemplate stringRedis;


    @GetMapping("/info/{id}")
    public User infoById(@PathVariable("id") Integer id){
        User user = userService.getById(id);
        return user;
    }
    @GetMapping("/list")
    public List<User> list() throws IllegalAccessException, InstantiationException {
        List<User> user = userService.list();
        List<Map<String, Object>> maps = BeanUtil.objectsToMaps(user);
        List<User> users = BeanUtil.mapsToObjects(maps, User.class);
        return users;
    }
    @GetMapping("/page/{page}")
    public Result<IPage<User>> pageList(@PathVariable("page") Integer pageNum, @RequestParam Integer pageSize){
        Page<User> page = new Page<>(pageNum,pageSize);
        IPage<User> userPage = userService.page(page);
        log.info("打印了日志 在userController");
        return Result.ok(userPage);
    }

    @GetMapping("/map/{id}")
    public Object infoMapById(@PathVariable("id") Integer id){
        User user = userService.getById(id);
        Map<String, Object> map = BeanUtil.beanToMap(user);
        return map;
    }
    @GetMapping("/test1")
    public Result test1(){
        throw new LocalException(Result.error(1000,"123"));
    }
    @GetMapping("/test2")
    public Result test2(){
        List<String> li = new ArrayList<>();
        li.get(1);
        return Result.ok();
    }
}
