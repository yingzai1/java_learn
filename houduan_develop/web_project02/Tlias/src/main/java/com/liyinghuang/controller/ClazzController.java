package com.liyinghuang.controller;

import com.liyinghuang.pojo.Clazz;
import com.liyinghuang.pojo.ClazzFindByIdResult;
import com.liyinghuang.pojo.ClazzResult;
import com.liyinghuang.pojo.Result;
import com.liyinghuang.service.ClazzServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
public class ClazzController {
    @Autowired
    ClazzServiceImp clazzServiceImp;
    //获取到现有的班级信息
    @GetMapping("/clazzs")
    public Result selectClazzByCase (String name,
                                     @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                                     @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end,
                                     @RequestParam(defaultValue = "0") Integer page,
                                     @RequestParam(defaultValue = "10") Integer pageSize
                                          )
    {
        log.info("进行班级列表查询");
        ClazzResult r = clazzServiceImp.selectClazzByCase(name, begin, end, page, pageSize);
        return Result.success(r);
    }
    @DeleteMapping("/clazzs/{id}")
    public Result deleteClazzById(@PathVariable(name="id") Integer id){
        clazzServiceImp.deleteClazzById(id);
        return Result.success();
    }
    @PostMapping("/clazzs")
    public Result insertClazz(@RequestBody Clazz clazz){
        log.info("接收到到的数据如下：{},{},{},{},{},{}",clazz.getName(),
                clazz.getRoom(),
                clazz.getBeginDate(),
                clazz.getEndDate(),
                clazz.getMasterId(),
                clazz.getSubject());
        clazzServiceImp.insertClazz(clazz);
        return Result.success();
    }
    //3.4根据ID进行查询
    @GetMapping("/clazzs/{id}")
    public Result selectClazzById(@PathVariable(name="id") Integer id){
        ClazzFindByIdResult clazz = clazzServiceImp.selectClazzById(id);
        return Result.success(clazz);
    }
    //3.5修改班级信息
    @PutMapping("/clazzs")
    public Result updateClazz(@RequestBody Clazz clazz){
        clazzServiceImp.updateClazz(clazz);
        return Result.success();
    }
    //3.6 查询所有班级
    @GetMapping("/clazzs/list")
    public Result getAllClazz(){
        log.info("进行查询所有班级操作！");
        return Result.success(clazzServiceImp.getAllClazz());
    }
}
