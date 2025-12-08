package com.liyinghuang.config;

import com.liyinghuang.interceptor.Demointerceptor;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class interceptor implements WebMvcConfigurer {
    @Autowired
    private Demointerceptor demointerceptor;
    
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        //注册自定义拦截器对象
        registry.addInterceptor(demointerceptor).addPathPatterns("/**");
    }
}
