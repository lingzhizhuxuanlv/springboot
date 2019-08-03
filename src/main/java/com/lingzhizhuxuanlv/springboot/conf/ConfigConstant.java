package com.lingzhizhuxuanlv.springboot.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ConfigConstant {

    @Autowired
    private Environment env;

    public static String jwtSecret;
    public static Long jwtExpiredTime;
    public static String jwtSubject;
    public static String jwtIssuer;

    @PostConstruct
    public void readConfig() {
        jwtSecret = env.getProperty("jwt.secret");
        jwtExpiredTime = Long.parseLong((env.getProperty("jwt.expired_time")));
        jwtSubject = env.getProperty("jwt.subject");
        jwtIssuer = env.getProperty("jwt.issuer");
    }

}
