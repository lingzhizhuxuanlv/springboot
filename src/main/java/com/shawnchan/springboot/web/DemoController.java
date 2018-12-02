package com.shawnchan.springboot.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class DemoController {

    @RequestMapping("/demo")
    public String toDemo(){
        return "demo";
    }

    @RequestMapping("/index")
    public String toIndex(){
        return "index";
    }

}
