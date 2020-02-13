package com.zaver.mp.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName : RedirectController
 * @Description TODO
 * @Date : 2020/1/19 11:02
 * @Author ABC
 * @Version 1.0
 * @Explanation ：
 */
@Controller
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/red")
    public void red(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("");
        return;
    }
}
