package com.zaver.mp.rbac.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zaver.mp.rbac.model.RbacPermission;

import java.util.List;
import java.util.Map;

public interface RbacPermissionService extends IService<RbacPermission>{

    List<Map<String,Object>> listAliveMenu();

    List<RbacPermission> listMenuByUser(Integer id);
}