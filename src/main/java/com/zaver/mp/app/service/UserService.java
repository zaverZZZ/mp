package com.zaver.mp.app.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zaver.mp.app.model.User;

public interface UserService extends IService<User>{

    // 手动分页查找
    IPage<User> pageAll(Page<User> page, String nickName);
}
