package com.zaver.mp.sys.rbac.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zaver.mp.sys.rbac.dao.RbacRoleDao;
import com.zaver.mp.sys.rbac.model.RbacRole;

import com.zaver.mp.sys.rbac.service.RbacRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RbacRoleServiceImpl extends ServiceImpl<RbacRoleDao, RbacRole> implements RbacRoleService{

    @Autowired
    private RbacRoleDao roleDao;

    @Override
    public List<RbacRole> getRoleByUserId(Integer id) {
        List<RbacRole> li = roleDao.getRoleByUserId(id);
        return li;
    }
}