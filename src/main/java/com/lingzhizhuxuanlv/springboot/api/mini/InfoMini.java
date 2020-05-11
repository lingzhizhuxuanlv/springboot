package com.lingzhizhuxuanlv.springboot.api.mini;

import com.lingzhizhuxuanlv.springboot.model.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Api(value = "小程序信息接口",tags = "小程序信息接口")
@RestController
@RequestMapping("/mini")
public class InfoMini {

    @ApiOperation(value = "小程序信息列表")
    @RequestMapping(value = "/info/list" ,method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "id",paramType = "query",dataType = "int", required = true),
            @ApiImplicitParam(name = "info",value = "信息",paramType = "query",dataType = "string", required = true)
    })
    public Object aList(Integer id, String info){
        List<Object> list = new ArrayList<>();
        list.add(id);
        list.add(info);
        return Result.buildOK("获取成功",list);
    }

    @ApiOperation(value = "小程序动态信息列表")
    @RequestMapping(value = "/info/listAuto/{id}/{info}" ,method = RequestMethod.GET)
    public Object bList(@PathVariable("id") Integer id, @PathVariable("info") String info){
        List<Object> list = new ArrayList<>();
        list.add(id);
        list.add(info);
        return Result.buildOK("获取成功",list);
    }

}
