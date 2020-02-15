package com.zaver.mp.sys.support.controller;

import com.zaver.mp.sys.support.service.SupportService;
import com.zaver.mp.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName : SupportController
 * @Description TODO
 * @Date : 2020/2/15 2:29
 * @Author ABC
 * @Version 1.0
 * @Explanation ：
 */
@RestController
@RequestMapping("/sys/support")
public class SupportController {

    @Autowired
    private SupportService supportService;

    @GetMapping("/token")
    public Result createIdempotentToken(){
        String token = supportService.getIdempotentToken();
        return Result.ok(token);
    }

}
