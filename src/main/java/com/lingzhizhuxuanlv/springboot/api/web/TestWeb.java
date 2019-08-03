package com.lingzhizhuxuanlv.springboot.api.web;

import com.lingzhizhuxuanlv.springboot.aop.WebLog;
import com.lingzhizhuxuanlv.springboot.model.Admin;
import com.lingzhizhuxuanlv.springboot.model.Result;
import com.lingzhizhuxuanlv.springboot.service.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "后台测试接口",tags = "后台测试接口")
@RestController
@RequestMapping("/web")
public class TestWeb {

    @Autowired
    private AdminService adminService;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @GetMapping("/test/mysql")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "权限凭证", dataType = "string", paramType = "header")
    })
    @WebLog(methodName = "mysql测试")
    public Object mysqlTest(){
        try{
            Admin admin =  adminService.selectByPrimaryKey(1L);
            return Result.buildOK("获取成功", admin);
        }catch(Exception e){
            return Result.buildERROR("获取失败");
        }
    }

    @GetMapping("/test/redis")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "权限凭证", dataType = "string", paramType = "header")
    })
    @WebLog(methodName = "redis测试")
    public Object redisTest(){
        try{
            Admin admin =  adminService.selectByPrimaryKey(2L);
            redisTemplate.opsForValue().set("2",admin.getName());
            String name = (String)redisTemplate.opsForValue().get("2");
            return Result.buildOK("获取成功", name);
        }catch(Exception e){
            return Result.buildERROR("获取失败");
        }
    }

}
