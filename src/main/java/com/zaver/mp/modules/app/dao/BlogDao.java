package com.zaver.mp.modules.app.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zaver.mp.modules.app.model.Blog;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogDao extends BaseMapper<Blog>{

    @Delete("truncate blog")
    int del();

    @Insert("insert into blog (title) values(#{a.title})")
    int ins(@Param("a") Blog blog);

}