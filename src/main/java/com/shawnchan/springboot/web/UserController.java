package com.shawnchan.springboot.web;

import com.shawnchan.springboot.aop.WebLog;
import com.shawnchan.springboot.model.Result;
import com.shawnchan.springboot.model.User;
import com.shawnchan.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @RequestMapping("/mysqlTest")
    @ResponseBody
    @WebLog(methodName = "mysql测试")
    public Object mysqlTest(){
        try{
            User user =  userService.selectByPrimaryKey(1);
            return Result.buildOK("获取成功",user);
        }catch(Exception e){
            return Result.buildERROR("获取失败");
        }
    }

    @RequestMapping("/redisTest")
    @ResponseBody
    @WebLog(methodName = "redis测试")
    public Object redisTest(){
        try{
            User user =  userService.selectByPrimaryKey(2);
            redisTemplate.opsForValue().set("2",user.getName());
            return Result.buildOK("获取成功",redisTemplate.opsForValue().get("2"));
        }catch(Exception e){
            return Result.buildERROR("获取失败");
        }
    }

}
