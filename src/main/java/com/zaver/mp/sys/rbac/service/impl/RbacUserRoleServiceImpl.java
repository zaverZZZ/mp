package com.zaver.mp.sys.rbac.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zaver.mp.sys.rbac.dao.RbacUserRoleDao;
import com.zaver.mp.sys.rbac.model.RbacUserRole;

import com.zaver.mp.sys.rbac.service.RbacUserRoleService;
import org.springframework.stereotype.Service;

@Service
public class RbacUserRoleServiceImpl extends ServiceImpl<RbacUserRoleDao, RbacUserRole> implements RbacUserRoleService{

}