package com.liyinghuang.controller;

import com.liyinghuang.pojo.PageResult;
import com.liyinghuang.pojo.Result;
import com.liyinghuang.pojo.Student;
import com.liyinghuang.service.StuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    //4.2 删除学员
    @DeleteMapping("/students/{ids}")
    public Result deleteStuById(@PathVariable List<Integer> ids) {
        stuService.deleteStuById(ids);
        return Result.success();
    }
    //4.3 添加学员
    @PostMapping("/students")
    public Result addStu(@RequestBody Student student) {
        stuService.addStu(student);
        return Result.success();
    }
    //4.4 根据id查找学员
    @GetMapping("/students/{id}")
    public Result findStuById(@PathVariable Integer id) {
        log.info("根据id查找学员");
        Student stuById = stuService.findStuById(id);
        return Result.success(stuById);
    }
    //4.5 修改学员
    @PutMapping("/students")
    public Result updateStu(@RequestBody Student student) {
        log.info("修改学员");
        stuService.updateStu(student);
        return Result.success();
    }
    //4.6 违纪处理
    @PutMapping("/students/violation/{id}/{score}")
    public Result violation(@PathVariable Integer id, @PathVariable Integer score) {
        stuService.violation(id,score);
        return Result.success();
    }
}
