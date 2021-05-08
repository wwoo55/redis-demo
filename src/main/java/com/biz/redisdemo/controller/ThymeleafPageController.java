package com.biz.redisdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @projectName: redis-demo
 * @className: ThymeleafPageController
 * @description: 页面跳转 controller
 * @author: xy
 * @time: 2021/5/7 11:34
 */
@Controller
public class ThymeleafPageController {

    @GetMapping("/index")
    public String toIndex(){
        return "index";
    }

    @GetMapping("/page/student-manage")
    public String toStudentManage(){
        return "student-manage";
    }
}
