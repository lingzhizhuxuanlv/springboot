package com.lingzhizhuxuanlv.springboot.conf;

import com.lingzhizhuxuanlv.springboot.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.servlet.config.annotation.*;

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
                .addResourceLocations("file:" + realPath + "/");
    }

    /**
     * redisTemplate序列化
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        JdkSerializationRedisSerializer jdkSerializationRedisSerializer = new JdkSerializationRedisSerializer();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        // 设置value和key的序列化规则
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setValueSerializer(jdkSerializationRedisSerializer);
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        redisTemplate.setHashValueSerializer(jdkSerializationRedisSerializer);
        return redisTemplate;
    }

    /**
     * 设置默认跳转页
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/","/index");
    }

    /**
     *
     * 配置拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/web/admin/login")
                .excludePathPatterns("/app/admin/login")
                .excludePathPatterns("/swagger-ui.html")
                .excludePathPatterns("/swagger-resources/**")
                .excludePathPatterns("/v2/**")
                .excludePathPatterns("/webjars/**")
                .excludePathPatterns("/file/**")
                .excludePathPatterns("/mini/**");
    }

    /**
     * 配置跨域
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //设置允许跨域的路径
        registry.addMapping("/**")
                //设置允许跨域请求的域名
                .allowedOrigins("*")
                //是否允许客户端发送cookie信息
                .allowCredentials(true)
                //放行哪些原始域(请求方式)
                .allowedMethods("*")
                //放行哪些原始域(头部信息)
                .allowedHeaders("*")
                //跨域允许时间
                .maxAge(1800);
    }

    /**
     * 配置jwt
     */
    @Bean
    public JwtUtil jwtUtil(){
        JwtUtil jwtUtil = new JwtUtil();
        jwtUtil.setSecret(env.getProperty("jwt.secret"));
        jwtUtil.setExpiredTime(env.getProperty("jwt.expired_time"));
        jwtUtil.setSubject(env.getProperty("jwt.subject"));
        jwtUtil.setIssuer(env.getProperty("jwt.issuer"));
        return jwtUtil;
    }

}
