package com.zaver.mp.sys.rbac.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zaver.mp.sys.rbac.dao.RbacUserDao;
import com.zaver.mp.sys.rbac.model.RbacUser;

import com.zaver.mp.sys.rbac.service.RbacUserService;
import com.zaver.mp.utils.StringUtils;
import com.zaver.mp.utils.security.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class RbacUserServiceImpl extends ServiceImpl<RbacUserDao, RbacUser> implements RbacUserService{
    @Autowired
    private RbacUserDao userDao;

    @Override
    public RbacUser getOneByUserName(String userName){
        RbacUser rbacUser = userDao.oneByUserName(userName);
        return rbacUser;
    }

    @Override
    public boolean saveRbacUser(RbacUser user) {
        String salt = StringUtils.getIntegerString(16);
        String password = ShiroUtils.sha256(user.getPassword(), salt);
        user.setSalt(salt);
        user.setPassword(password);
        user.setStatus(1);
        user.setCreateTime(new Date().getTime());
        boolean save = this.save(user);
        return save;
    }
}