package com.lingzhizhuxuanlv.springboot;

import cn.hutool.http.HttpRequest;

import java.io.File;

public class MainDemo {

    public static void main(String[] args) {

        System.out.println(HttpRequest.post("http://localhost:8080/springboot/web/admin/login")
                .form("username","admin")
                .form("password","123456")
                .execute()
                .body());

//        System.out.println(HttpRequest.get("http://localhost:8080/springboot/web/test/mysql")
//                .header("token","eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJzcHJpbmdib290Iiwic3ViIjoidG9rZW5DaGVjayIsImp0aSI6IjEiLCJpYXQiOjE1NjQ4MjIwOTksImV4cCI6MTU2NDgyMjk5OSwidXNlcklkIjoxfQ.k_aQVImBy4a25uyE69cahpFR2UGwQaJy973DjjKkLYI")
//                .execute()
//                .body());

//        File file  = new File("C:\\Users\\lenovo\\Downloads\\ArcSoft_ArcFace_Java_Windows_x64_V2.2\\1.jpg");
//        System.out.println(HttpRequest.post("http://localhost:8080/springboot/web/upload/file")
//                .header("token","eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJzcHJpbmdib290Iiwic3ViIjoidG9rZW5DaGVjayIsImp0aSI6IjEiLCJpYXQiOjE1NjQ4MjIwOTksImV4cCI6MTU2NDgyMjk5OSwidXNlcklkIjoxfQ.k_aQVImBy4a25uyE69cahpFR2UGwQaJy973DjjKkLYI")
//                .form("file",file)
//                .execute()
//                .body());
    }

}
