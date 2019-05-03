package com.zaver.mp.rbac.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zaver.mp.rbac.dao.RbacRolePermissionDao;
import com.zaver.mp.rbac.model.RbacRolePermission;

import com.zaver.mp.rbac.service.RbacRolePermissionService;
import org.springframework.stereotype.Service;

@Service
public class RbacRolePermissionServiceImpl extends ServiceImpl<RbacRolePermissionDao, RbacRolePermission> implements RbacRolePermissionService{

}