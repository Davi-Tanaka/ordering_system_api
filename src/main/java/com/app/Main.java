package com.app;

import com.app.interfaces.interceptor.AuthorizationInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableAutoConfiguration 
@SpringBootApplication
@ComponentScan("com.app")
@Configuration
public class Main implements WebMvcConfigurer {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Main.class, args);
    }
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthorizationInterceptor())
                .addPathPatterns("/*")
                .excludePathPatterns(
                        "/user/login",
                        "/user/signup",
                        "/swagger-ui/index.html",
                        "/docs/"
                );
    }
}
