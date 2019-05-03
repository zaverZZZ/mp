package com.zaver.mp.rbac.controller;

import com.zaver.mp.rbac.model.RbacPermission;
import com.zaver.mp.rbac.model.RbacUser;
import com.zaver.mp.rbac.service.RbacPermissionService;
import com.zaver.mp.utils.BeanUtil;
import com.zaver.mp.utils.Result;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
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
@RequestMapping("/rbacPermission")
public class RbacPermissionController {

    @Autowired
    private RbacPermissionService permissionService;

    @RequestMapping("/all")
    public Result all(){
        List<Map<String,Object>> menu = permissionService.listAliveMenu();
        return Result.ok(menu);
    }

    @RequestMapping("/menu")
    public Result menu(){
        RbacUser user = (RbacUser)SecurityUtils.getSubject().getPrincipal();
        List<RbacPermission> menu = permissionService.listMenuByUser(user.getId());
        return Result.ok(menu);
    }
}
