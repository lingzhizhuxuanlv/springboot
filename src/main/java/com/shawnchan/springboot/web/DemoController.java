package com.shawnchan.springboot.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DemoController {

    @RequestMapping("/demo")
    private String toDemo(){
        return "demo";
    }

    @RequestMapping("/index")
    private String toIndex(){
        return "index";
    }

}
