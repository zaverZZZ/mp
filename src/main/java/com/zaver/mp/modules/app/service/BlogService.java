package com.zaver.mp.modules.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zaver.mp.modules.app.model.Blog;

public interface BlogService extends IService<Blog>{

    int del();
}