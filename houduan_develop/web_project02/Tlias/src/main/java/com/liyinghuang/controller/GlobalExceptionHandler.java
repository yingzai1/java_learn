package com.liyinghuang.controller;

import com.liyinghuang.pojo.Result;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice//声明这是一个全局异常处理类
//@RestControllerAdvice = @ControllerAdvice + @ResponseBody
public class GlobalExceptionHandler {
    @ExceptionHandler
    public Result handleException(DuplicateKeyException e){
        String s = e.getMessage().substring(e.getMessage().indexOf("Duplicate entry")).split(" ")[2];
        return Result.error("不好意思"+s+"字段与数据库重复！");
    }
}
