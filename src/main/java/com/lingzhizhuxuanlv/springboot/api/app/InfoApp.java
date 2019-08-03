package com.lingzhizhuxuanlv.springboot.api.app;

import com.lingzhizhuxuanlv.springboot.model.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Api(value = "APP信息接口",tags = "APP信息接口")
@RestController
@RequestMapping("/app")
public class InfoApp {

    @ApiOperation(value = "APP信息列表")
    @RequestMapping(value = "/info/list" ,method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "id",paramType = "query",dataType = "int", required = true),
            @ApiImplicitParam(name = "info",value = "信息",paramType = "query",dataType = "string", required = true),
            @ApiImplicitParam(name = "token", value = "权限凭证", dataType = "string", paramType = "header")
    })
    public Object list(Integer id, String info , HttpServletRequest request){
        try{
            List<Object> list = new ArrayList<>();
            list.add(id);
            list.add(info);
            return Result.buildOK("获取成功",list);
        }catch (Exception e){
            return Result.buildERROR("获取失败");
        }
    }
}
