package com.lingzhizhuxuanlv.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * 打成war包需要继承SpringBootServletInitializer并重写configure方法
 */
@SpringBootApplication
public class SpringbootApplication extends SpringBootServletInitializer {

    /**
     * 部署war包配置
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        //指向原先用main方法执行的Application启动类
        return application.sources(SpringbootApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringbootApplication.class, args);
    }

}
