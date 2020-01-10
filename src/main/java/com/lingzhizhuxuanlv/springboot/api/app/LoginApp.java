package com.lingzhizhuxuanlv.springboot.api.app;

import com.lingzhizhuxuanlv.springboot.model.Result;
import com.lingzhizhuxuanlv.springboot.util.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "APP登录接口",tags = "APP登录接口")
@RestController
@RequestMapping("/app")
public class LoginApp {

    @ApiOperation("登录接口")
    @PostMapping("/admin/login")
    public Object login(String loginName, String password){
        return Result.buildOK("登录成功", JwtUtil.createJwt(1L,loginName));
    }

}
