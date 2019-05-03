package com.zaver.mp.rbac.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zaver.mp.rbac.dao.RbacUserRoleDao;
import com.zaver.mp.rbac.model.RbacUserRole;

import com.zaver.mp.rbac.service.RbacUserRoleService;
import org.springframework.stereotype.Service;

@Service
public class RbacUserRoleServiceImpl extends ServiceImpl<RbacUserRoleDao, RbacUserRole> implements RbacUserRoleService{

}