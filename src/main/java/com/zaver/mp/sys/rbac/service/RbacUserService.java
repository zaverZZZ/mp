package com.zaver.mp.sys.rbac.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zaver.mp.sys.rbac.model.RbacUser;

public interface RbacUserService extends IService<RbacUser>{

    RbacUser getOneByUserName(String userName);

    boolean saveRbacUser(RbacUser user);
}