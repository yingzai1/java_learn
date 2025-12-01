package com.liyinghuang.controller;

import com.liyinghuang.pojo.Emp;
import com.liyinghuang.pojo.LoginInfo;
import com.liyinghuang.pojo.Result;
import com.liyinghuang.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class LoginController {
    @Autowired
    EmpService empService;
    @PostMapping("/login")
    public Result login(@RequestBody Emp emp){
        log.info("员工来登录了~");
        LoginInfo login = empService.login(emp);
        if (login!=null) {
            return Result.success(login);
        }
        else{
            return Result.error("用户名或者密码错误~");
        }
    }
}
