package com.zaver.mp.sys.rbac.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zaver.mp.sys.rbac.model.RbacRole;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RbacRoleDao extends BaseMapper<RbacRole>{

    List<RbacRole> getRoleByUserId(Integer id);
}