package com.liyinghuang.controller;

import com.liyinghuang.mapper.EmpMapper;
import com.liyinghuang.pojo.Emp;
import com.liyinghuang.pojo.PageResult;
import com.liyinghuang.pojo.Result;
import com.liyinghuang.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@RequestMapping("/emps")
@RestController
public class EmpController {
    @Autowired
    private EmpService empService;
//    @GetMapping
//    public Result page(Integer page,Integer pageSize){
//        log.info("进行无条件分页处理ing-----------");
//        PageResult<Emp> control_result =empService.page(page,pageSize);
//        return Result.success(control_result);
//    }
    @GetMapping
    public Result page(String name,
                       Integer gender,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end,
                       @RequestParam(defaultValue = "1")Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize
                       )
    {
        log.info("输出的请求参数：{},{},{},{},{},{}",name,gender,begin,end,page,pageSize);
        PageResult<Emp> result= empService.page(name,gender,begin,end,page,pageSize);
        return Result.success(result);
    }

    //添加员工信息
    @PostMapping
    public Result add(@RequestBody Emp emp) throws Exception {
        log.info("{}",emp);
        empService.add(emp);
        return Result.success();
    }
//    @DeleteMapping
//    public Result delete(Integer []ids){
//            //根据ID值进行删除
//            for (int i = 0; i < ids.length; i++) {
//                empService.delete(ids[i]);
//            }
//        return Result.success();
//    }
    @DeleteMapping
    public Result delete(@RequestParam List<Integer> ids) {
        //根据ID值进行删除
        empService.delete(ids);
        return Result.success();
    }
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable(name="id") Integer id){
        Emp data=empService.getInfo(id);
        return Result.success(data);
    }
    @PutMapping
    public Result update(@RequestBody Emp emp){
        log.info("控制层接受到的输入:{}",emp);
        empService.update(emp);
        return Result.success();
    }
}
//查询出所有员工的信息及其所属部门的名称
//select emp.* , dept.name from emp left inner dept on emp.dept_id=dept.id