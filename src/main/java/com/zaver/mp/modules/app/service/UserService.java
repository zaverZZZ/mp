package com.zaver.mp.modules.app.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zaver.mp.modules.app.model.User;

public interface UserService extends IService<User>{

    // 手动分页查找
    IPage<User> pageAll(Page<User> page, String nickName);

    boolean save(User user);
}
