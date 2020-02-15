package com.zaver.mp.sys.rbac.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zaver.mp.sys.rbac.model.RbacUser;
import org.springframework.stereotype.Repository;

@Repository
public interface RbacUserDao extends BaseMapper<RbacUser>{
    RbacUser oneByUserName(String userName);
}