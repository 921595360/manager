package com.silence.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by silence on 2018/1/4.
 */
@Controller
public class TestController {
    @RequestMapping("welcome")
    public String welcome(Model model, HttpServletRequest request) {
        System.out.println("TestController.welcome");
        //添加单个记录
        model.addAttribute("userName","zhangsan");
        //集合记录
        model.addAttribute("names",new String[]{"a","b","c"});
        request.getSession().setAttribute("sessionData","session数据");
        return "test";
    }

}
