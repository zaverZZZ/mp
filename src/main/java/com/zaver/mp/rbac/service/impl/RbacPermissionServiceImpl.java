package com.zaver.mp.rbac.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zaver.mp.rbac.dao.RbacPermissionDao;
import com.zaver.mp.rbac.model.RbacPermission;

import com.zaver.mp.rbac.service.RbacPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RbacPermissionServiceImpl extends ServiceImpl<RbacPermissionDao, RbacPermission> implements RbacPermissionService{

    @Autowired
    private RbacPermissionDao permissionDao;
    @Override
    public List<Map<String,Object>> listAliveMenu() {
        List<Map<String,Object>> menu = permissionDao.listAliveMenu();
        return menu;
    }

    @Override
    public List<RbacPermission> listMenuByUser(Integer id) {
        List<RbacPermission> menu = permissionDao.listMenuByUser(id);
        return menu;
    }
}