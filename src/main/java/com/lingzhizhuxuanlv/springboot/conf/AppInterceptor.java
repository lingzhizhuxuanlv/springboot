package com.lingzhizhuxuanlv.springboot.conf;

import cn.hutool.json.JSONObject;
import com.lingzhizhuxuanlv.springboot.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Objects;

public class AppInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler) throws Exception {
//        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>preHandle");
        if(request.getMethod().equals("OPTIONS")){
            return true;
        }
        //token验证
        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            if ("token".equals(key)) {
                Claims claims = JwtUtil.verifyJwt(value);
                if(Objects.nonNull(claims)){
                    return true;
                }
            }
        }
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "*");
        PrintWriter out;
        JSONObject result = new JSONObject();
        result.put("code", 4001);
        result.put("msg", "登录信息失效，请重新登录");
        result.put("data","");
        out = response.getWriter();
        out.append(result.toString());
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>AppInterceptor:"+request.getRequestURL());
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,Object handler, ModelAndView modelAndView) throws Exception {
//        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,Object handler, Exception ex) throws Exception {
//        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>afterCompletion");
    }

}
