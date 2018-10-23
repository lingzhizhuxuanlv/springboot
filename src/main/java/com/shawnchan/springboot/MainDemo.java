package com.shawnchan.springboot;

import cn.hutool.http.HttpUtil;

public class MainDemo {

    public static void main(String[] args) throws Exception{
        HttpUtil.createPost("http://localhost:8080/tyjws-mini/api/member/message");
        System.out.println("");
    }

}
