package com.lingzhizhuxuanlv.springboot.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class DemoController {

    @RequestMapping("/demo")
    public String toDemo(HttpSession session){
        session.setAttribute("username","xiaoming");
        return "demo";
    }

    @RequestMapping("/index")
    public String toIndex(){
        return "index";
    }

}
