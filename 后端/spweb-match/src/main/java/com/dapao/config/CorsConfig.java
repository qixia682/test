package com.dapao.config;


//CorsConfig.java
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 允许跨域访问的路径
                .allowedOrigins("http://localhost:5174","http://localhost:5175") // 允许跨域请求的域名
                .allowedMethods("*") // 允许跨域请求的方法
                .allowedHeaders("*") // 允许跨域请求的header
                .allowCredentials(true); // 是否允许证书（cookies）
    }
}
