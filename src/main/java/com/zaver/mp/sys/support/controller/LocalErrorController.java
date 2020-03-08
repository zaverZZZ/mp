package com.zaver.mp.sys.support.controller;

import com.zaver.mp.utils.Result;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName : LocalErrorController
 * @Description TODO
 * @Date : 2019/7/21 23:56
 * @Author ABC
 * @Version 1.0
 * @Explanation ：
 */
@RestController
public class LocalErrorController implements ErrorController {

    private final static String errorPath = "/error";
    @Override
    public String getErrorPath() {
        return errorPath;
    }

    @RequestMapping("/error")
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public Result handleError(){
        return Result.error(404,"not found");
    }

}
