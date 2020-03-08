package com.zaver.mp.sys.rbac.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zaver.mp.sys.rbac.model.RbacPermission;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface RbacPermissionDao extends BaseMapper<RbacPermission>{

    List<Map<String,Object>> listAliveMenu();

    // List<Integer> listMenuIdsByUser(Integer id);
}