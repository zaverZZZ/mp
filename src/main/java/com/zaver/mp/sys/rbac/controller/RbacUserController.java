package com.zaver.mp.sys.rbac.controller;

import com.zaver.mp.sys.rbac.model.RbacUser;
import com.zaver.mp.sys.rbac.service.RbacUserService;
import com.zaver.mp.utils.Result;
import com.zaver.mp.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName : RbacUserController
 * @Description TODO
 * @Date : 2019/4/16 20:17
 * @Author ABC
 * @Version 1.0
 * @Explanation ：
 */
@Controller
@RequestMapping("/sys/user")
public class RbacUserController {
    @Autowired
    private RbacUserService userService;

    @RequestMapping("/save")
    @ResponseBody
    public Result saveUser(RbacUser user){
        if(user==null||StringUtils.isNull(user.getUsername())||StringUtils.isNull(user.getPassword())){
            return Result.fail();
        }
        userService.saveRbacUser(user);
        return Result.ok();
    }

}
