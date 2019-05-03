package com.zaver.mp.rbac.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zaver.mp.rbac.dao.RbacRoleDao;
import com.zaver.mp.rbac.model.RbacRole;

import com.zaver.mp.rbac.service.RbacRoleService;
import org.springframework.stereotype.Service;

@Service
public class RbacRoleServiceImpl extends ServiceImpl<RbacRoleDao, RbacRole> implements RbacRoleService{

}