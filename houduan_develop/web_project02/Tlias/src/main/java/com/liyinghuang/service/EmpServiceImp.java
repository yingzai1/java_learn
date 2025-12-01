package com.liyinghuang.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.liyinghuang.mapper.EmpExprMapper;
import com.liyinghuang.mapper.EmpMapper;
import com.liyinghuang.pojo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.liyinghuang.utils.JwtUtils.generateJwt;

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

//    @Transactional(rollbackFor = Exception.class)
//    @Override
//    public void delete(Integer id) {
//        //删除员工信息
//        empMapper.delete(id);
//        //删除员工的经历信息
//        empExprMapper.delete(id);
//    }    @Transactional(rollbackFor = Exception.class)
    //使用foreach语句进行删除
    @Override
    public void delete(List<Integer> ids) {
        //删除员工信息
        empMapper.delete(ids);
        //删除员工的经历信息
        empExprMapper.delete(ids);
    }

    @Override
    public Emp getInfo(Integer id) {
        return empMapper.getInfo(id);
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(Emp emp) {
        //对基本属性进行配置
        emp.setUpdateTime(LocalDateTime.now());
        //保存基本属性(用id来进行位置确认)
        empMapper.update(emp);
        //清空工作经历
        empExprMapper.delete(Arrays.asList(emp.getId()));
        //保存新的员工经历
        List<EmpExpr> exprList = emp.getExprList();
        if(exprList!=null && !exprList.isEmpty()){//1、防止对象不存在。2、防止对象存在但里面没元素
            emp.getExprList().forEach(emp1->emp1.setEmpId(emp.getId()));
            log.info("工作经历数量:{}",exprList.size());
            empExprMapper.add_empExp(exprList);
        }
    }

    @Override
    public LoginInfo login(Emp emp) {
        //检查员工"username": "jinyong",
        //    "password": "123456"
        LoginInfo login = empMapper.login(emp);
        if(login!=null){
            //生成相应的pwt
            Map<String,Object> map = new HashMap<>();
            map.put("id",login.getId());
            map.put("username",login.getUsername());
            String s = generateJwt(map);
            login.setToken(s);
            return login;
        }
        return null;
    }
}
