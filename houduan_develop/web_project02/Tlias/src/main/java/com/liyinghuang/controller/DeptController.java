package com.liyinghuang.controller;

import com.liyinghuang.pojo.Dept;
import com.liyinghuang.pojo.Result;
import com.liyinghuang.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
//该注解有两个作用：1、将其交给IOC容器进行管理
//2、将方法的返回值直接作为响应数据，如果方法的返回值是对象或者集合则返回相应的json文件。
@Slf4j
public class DeptController{
    @Autowired
    DeptService deptService;
    @GetMapping("/depts")
    public Result list(){
//        System.out.println("输出全部部门的信息");
        log.info("查询全部部门信息");
        List<Dept> deptList= deptService.findAll();
        return Result.success(deptList);
    }
    @DeleteMapping("/depts")
    public Result delete(Integer id){
        System.out.println("删除部门信息"+id);
        deptService.delete(id);
        return Result.success();
    }
    @PostMapping("/depts")
    public Result insert(@RequestBody Dept dept){
        deptService.insert(dept);
        //System.out.println("添加部门信息成功!");
        return Result.success();
    }
    //下面方法根据输入的id返回相应的部门信息
    @GetMapping("/depts/{id}")//这里使用路径变量来传递参数
    public Result get(@PathVariable("id") Integer  id){
        Object byId = deptService.findById(id);
        com.liyinghuang.pojo.Result result;
        result=Result.success(byId);
        return result;
    }
    //修改部门信息
    @PutMapping("/depts")
//    @RequestMapping(value = "/dept", method = RequestMethod.PUT)
    public Result update(@RequestBody Dept dept){
        log.info("修改部门信息");
        deptService.update(dept);
        return Result.success();
    }
}
