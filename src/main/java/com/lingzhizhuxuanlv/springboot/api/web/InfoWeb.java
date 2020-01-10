package com.lingzhizhuxuanlv.springboot.api.web;

import com.lingzhizhuxuanlv.springboot.model.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Api(value = "WEB信息接口",tags = "WEB信息接口")
@RestController
@RequestMapping("/web")
public class InfoWeb {

    @ApiOperation(value = "WEB信息列表")
    @RequestMapping(value = "/info/list" ,method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "id",paramType = "query",dataType = "int", required = true),
            @ApiImplicitParam(name = "info",value = "信息",paramType = "query",dataType = "string", required = true),
    })
    public Object list(Integer id, String info){
        List<Object> list = new ArrayList<>();
        list.add(id);
        list.add(info);
        return Result.buildOK("获取成功",list);
    }

}
