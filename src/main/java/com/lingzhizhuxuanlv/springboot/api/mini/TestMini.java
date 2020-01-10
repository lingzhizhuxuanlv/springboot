package com.lingzhizhuxuanlv.springboot.api.mini;

import com.lingzhizhuxuanlv.springboot.aop.WebLog;
import com.lingzhizhuxuanlv.springboot.model.Admin;
import com.lingzhizhuxuanlv.springboot.model.Result;
import com.lingzhizhuxuanlv.springboot.service.AdminService;
import io.swagger.annotations.Api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "小程序测试接口",tags = "小程序测试接口")
@RestController
@RequestMapping("/mini")
@Slf4j
public class TestMini {

    @Autowired
    private AdminService adminService;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @GetMapping("/test/mysql")
    @WebLog(methodName = "mysql测试")
    public Object mysqlTest(){
        log.trace("这是trace日志...");
        log.debug("这是debug日志...");
        log.info("这是info日志...");
        log.warn("这是warn日志...");
        log.error("这是error日志...");
        Admin admin =  adminService.selectByPrimaryKey(1L);
        return Result.buildOK("获取成功", admin);
    }

    @GetMapping("/test/redis")
    @WebLog(methodName = "redis测试")
    public Object redisTest(){
        Admin admin =  adminService.selectByPrimaryKey(2L);
        redisTemplate.opsForValue().set("2",admin.getName());
        String name = (String)redisTemplate.opsForValue().get("2");
        return Result.buildOK("获取成功", name);
    }

}
