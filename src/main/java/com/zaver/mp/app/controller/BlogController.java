package com.zaver.mp.app.controller;

import com.zaver.mp.app.model.Blog;
import com.zaver.mp.app.service.BlogService;
import com.zaver.mp.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @ClassName : BlogController
 * @Description TODO
 * @Date : 2019/5/3 20:47
 * @Author ABC
 * @Version 1.0
 * @Explanation ：
 */
@RestController
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @RequestMapping("/{id}")
    public Result<Blog> info(@PathVariable("id") Integer id){
        Blog blog = blogService.getById(id);
        return Result.ok(blog);
    }
    @RequestMapping("/list")
    public Result<List<Blog>> list(){
        List<Blog> list = blogService.list();
        return Result.ok(list);
    }
    @RequestMapping("/add")
    public Result add(@RequestBody Blog blog){
        boolean save = blogService.save(blog);
        return Result.ok(save);
    }
    @RequestMapping("/update")
    public Result update(@RequestBody Blog blog){
        boolean b = blogService.updateById(blog);
        if(b){
            return Result.ok(blogService.getById(blog.getId()));
        }else{
            return Result.fail();
        }
    }
    @RequestMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Integer id){
        boolean b = blogService.removeById(id);
        return Result.ok(b);
    }
}
