package com.liyinghuang.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.liyinghuang.mapper.EmpExprMapper;
import com.liyinghuang.mapper.EmpMapper;
import com.liyinghuang.pojo.Emp;
import com.liyinghuang.pojo.EmpExpr;
import com.liyinghuang.pojo.EmpLog;
import com.liyinghuang.pojo.PageResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Options;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
@Slf4j
@Service//声明为springboot IOC容器中的bean
public class EmpServiceImp implements EmpService {
    @Autowired
    EmpMapper empMapper;
    @Autowired
    EmpExprMapper empExprMapper;
    @Autowired
    EmpLogService empLogService;
//    //无条件分页查询方法一：
//    @Override
//    public PageResult<Emp> page(Integer page, Integer pageSize) {
//        Long total = empMapper.count();
//        Integer pageNum = (page - 1) * pageSize;
//        List<Emp>rows=empMapper.list(pageNum,pageSize);
//        PageResult<Emp> pageResult = new PageResult<>(total,rows);
//        return pageResult;
//    }
    //无条件分页查询方法二：
//    @Override
//    public PageResult<Emp> page(Integer page, Integer pageSize) {
//        //1.设置分页参数
//        PageHelper.startPage(page,pageSize);
//        //2.执行查询
//        List<Emp> empList = empMapper.list();
//        //3.解析查询结果，并封装
//        Page<Emp> p=(Page<Emp>)empList;//就是page是List的子类，所以可以进行强制类型转换
//        return new PageResult<Emp>(p.getTotal(),p.getResult());
//    }
    @Override
    public PageResult<Emp> page(String name, Integer gender, LocalDate begin, LocalDate end, Integer page, Integer pageSize) {
        //1、设置分页参数
        PageHelper.startPage(page, pageSize);
        //2、执行查询
        List<Emp> empList = empMapper.list(name, gender, begin, end);
        Page<Emp> p=(Page<Emp>)empList;
        return new PageResult<Emp>(p.getTotal(),p.getResult());
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(Emp emp) throws Exception {
        try {
            //0、初始化一些数据
            emp.setCreateTime(LocalDateTime.now());
            emp.setUpdateTime(LocalDateTime.now());
            //1、保存员工信息到数据库
            empMapper.add_emp(emp);

            //检查在异常情况下，能否进行回滚
            //Integer a=1/0;

//        if(true){
//            throw new Exception("我来骗骗编译器~");
//        }

            //2、保存员工经历到数据库
            List<EmpExpr> empExpList = emp.getExprList();//获取一个工作经历集合
            if(!CollectionUtils.isEmpty(empExpList)){
                empExpList.forEach(emp1->emp1.setEmpId(emp.getId()));//为每段工作经历加上对应的员工ID
                log.info("{}",empExpList.size());
                empExprMapper.add_empExp(empExpList);
            }
        } finally {
            //记录操作日志
            EmpLog empLog = new EmpLog(null, LocalDateTime.now(), emp.toString());
            empLogService.insertLog(empLog);
        }
    }
}
