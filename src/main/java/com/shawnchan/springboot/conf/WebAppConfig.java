package com.shawnchan.springboot.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

@Configuration
public class WebAppConfig implements WebMvcConfigurer {

    @Autowired
    private Environment env;

    /**
     * tomcat资源映射
     * http://localhost:8080/demo/upload/**
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String realPath = "";
        String os = System.getProperty("os.name");
        if (os.toLowerCase().startsWith("win")) {
            realPath = env.getProperty("upload.w-upload");
        } else {
            realPath = env.getProperty("upload.l-upload");
        }
        //下载路径
        String download = env.getProperty("download");
        //设置下载路径映射
        registry.addResourceHandler(download + "/**")
                .addResourceLocations("file:"+realPath+"/");
    }

    /**
     * freemarker视图解析
     */
    @Bean
    public FreeMarkerViewResolver freeMarkerViewResolver() {
        //templates为根目录，配置视图解析
        //"/WEB-INF/"   ".html"
        FreeMarkerViewResolver freeMarkerViewResolver = new FreeMarkerViewResolver("", ".ftl");
        //配置默认插值
        freeMarkerViewResolver.setViewClass(MyFreeMarkerView.class);
        //开启缓存
        freeMarkerViewResolver.setCache(true);
        //配置内容类型
        freeMarkerViewResolver.setContentType("text/html;charset=UTF-8");
        return freeMarkerViewResolver;
    }

}