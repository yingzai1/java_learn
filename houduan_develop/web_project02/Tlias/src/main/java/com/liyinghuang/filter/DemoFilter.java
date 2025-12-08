package com.liyinghuang.filter;

import com.liyinghuang.utils.CurrentHolder;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletMapping;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

import static com.liyinghuang.utils.JwtUtils.parseJwt;

@Slf4j
//@WebFilter("/*")//配置拦截资源的路径
public class DemoFilter implements Filter {//这里filter千万不要导错包
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //判断当前是否为登录请求？
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        boolean contains = request.getRequestURI().contains("/login");
        if (contains) {
            log.info("现在进行一个登录请求~");
            filterChain.doFilter(request,response);//放行
        }
        String token = request.getHeader("token");
        //对token的存在进行检查
        if(token == null){
            log.info("获取到jwt令牌为空, 返回错误结果");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        //对token的合法性进行检查
        try {
            Claims claims = parseJwt(token);
        } catch (Exception e) {
            log.info("非法的token~");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        //放行
        filterChain.doFilter(request,response);
        CurrentHolder.remove();//释放资源
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
