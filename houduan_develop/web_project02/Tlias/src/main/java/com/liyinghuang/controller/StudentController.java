package com.liyinghuang.controller;

import com.liyinghuang.pojo.PageResult;
import com.liyinghuang.pojo.Result;
import com.liyinghuang.pojo.Student;
import com.liyinghuang.service.StuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class StudentController {
    @Autowired
    StuService stuService;
    
    //4.1 学员列表查询
    @GetMapping("/students")
    public Result findStuByCase(String name,
                                Integer degree,
                                Integer clazzId,
                                @RequestParam(defaultValue = "1") Integer page,
                                @RequestParam(defaultValue = "10") Integer pageSize) {
        log.info("学员列表查询: name={}, degree={}, clazzId={}, page={}, pageSize={}", 
                 name, degree, clazzId, page, pageSize);
        
        PageResult<Student> pageResult = stuService.findStuByCase(name, degree, clazzId, page, pageSize);
        return Result.success(pageResult);
    }
}
