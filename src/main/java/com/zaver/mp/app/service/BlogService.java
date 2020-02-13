package com.zaver.mp.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zaver.mp.app.model.Blog;

public interface BlogService extends IService<Blog>{

    int del();
}