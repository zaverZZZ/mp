package com.zaver.mp.sys.rbac.controller;

import com.zaver.mp.sys.rbac.model.RbacPermission;
import com.zaver.mp.sys.rbac.model.RbacUser;
import com.zaver.mp.sys.rbac.service.RbacPermissionService;
import com.zaver.mp.utils.Result;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @ClassName : RbacPermissionController
 * @Description TODO
 * @Date : 2019/4/17 19:50
 * @Author ABC
 * @Version 1.0
 * @Explanation ：
 */
@RestController
@RequestMapping("/sys/permission")
public class RbacPermissionController {

    @Autowired
    private RbacPermissionService permissionService;

    @RequestMapping("/all")
    public Result all(){
        return Result.ok();
    }

    @RequestMapping("/menu")
    public Result menu(){
        RbacUser user = (RbacUser)SecurityUtils.getSubject().getPrincipal();
        return Result.ok();
    }
}
