package com.lingzhizhuxuanlv.springboot;

import cn.hutool.http.HttpRequest;

public class MainDemo {

    public static void main(String[] args) {
        System.out.println(HttpRequest.post("http://localhost:8080/springboot-app/app/login")
                .form("username","admin")
                .form("password","123456")
                .execute()
                .body());
        System.out.println(HttpRequest.get("http://localhost:8080/springboot-app/app/demo")
                .header("token","eyJhbGciOiJIUzI1NiJ9.eyJwYXNzd29yZCI6IjEyMzQ1NiIsImV4cCI6MTU1MDA1MzE1NywiaWF0IjoxNTQ5OTY2NzU3LCJqdGkiOiJ0b2tlbklkIiwidXNlcm5hbWUiOiJhZG1pbiJ9.bXCLBVPF6BewLTynhHkEPgasQvgHejKghZJ5CNmxHF4")
                .execute()
                .body());
    }

}
