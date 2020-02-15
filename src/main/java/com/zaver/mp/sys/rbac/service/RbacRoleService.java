package com.zaver.mp.sys.rbac.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zaver.mp.sys.rbac.model.RbacRole;

import java.util.List;

public interface RbacRoleService extends IService<RbacRole>{
    List<RbacRole> getRoleByUserId(Integer id);
}