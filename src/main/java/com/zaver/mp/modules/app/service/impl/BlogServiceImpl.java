package com.zaver.mp.modules.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zaver.mp.modules.app.model.Blog;
import com.zaver.mp.modules.app.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zaver.mp.modules.app.dao.BlogDao;

@Service
public class BlogServiceImpl extends ServiceImpl<BlogDao, Blog> implements BlogService {

    @Autowired
    private BlogDao blogDao;

    @Override
    public int del() {
        int i = blogDao.del();
        Blog blog = new Blog();
        blog.setAuthor("1111");
        blog.setTitle("2222");
        blogDao.ins(blog);
        return i;
    }
}