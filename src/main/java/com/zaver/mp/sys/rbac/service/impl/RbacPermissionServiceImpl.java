package com.zaver.mp.sys.rbac.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zaver.mp.sys.rbac.dao.RbacPermissionDao;
import com.zaver.mp.sys.rbac.model.RbacPermission;

import com.zaver.mp.sys.rbac.service.RbacPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RbacPermissionServiceImpl extends ServiceImpl<RbacPermissionDao, RbacPermission> implements RbacPermissionService{

}