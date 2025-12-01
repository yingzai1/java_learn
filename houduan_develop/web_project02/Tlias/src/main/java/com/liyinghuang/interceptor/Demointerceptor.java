package com.liyinghuang.interceptor;

import com.liyinghuang.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import static com.liyinghuang.utils.JwtUtils.parseJwt;

//声明一个拦截器
@Component
@Slf4j
public class Demointerceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean contains = request.getRequestURI().contains("/login");
        if (contains) {
            log.info("现在进行一个登录请求~");
            return true;
        }
        String token = request.getHeader("token");
        //对token的存在进行检查
        if(token == null){
            log.info("获取到jwt令牌为空, 返回错误结果");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        //对token的合法性进行检查
        try {
            Claims claims = parseJwt(token);
        } catch (Exception e) {
            log.info("非法的token~");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        //放行
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
