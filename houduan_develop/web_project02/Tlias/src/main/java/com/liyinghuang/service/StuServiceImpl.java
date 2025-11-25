package com.liyinghuang.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.liyinghuang.mapper.StuMapper;
import com.liyinghuang.pojo.PageResult;
import com.liyinghuang.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StuServiceImpl implements StuService{
    @Autowired
    StuMapper stuMapper;
    
    @Override
    public PageResult<Student> findStuByCase(String name, Integer degree, Integer clazzId, Integer page, Integer pageSize) {
        // 设置分页参数
        PageHelper.startPage(page, pageSize);
        // 执行查询
        List<Student> list = stuMapper.findStuByCase(name, degree, clazzId);
        // 获取分页信息
        Page<Student> p = (Page<Student>) list;
        // 封装结果
        return new PageResult<>(p.getTotal(), p.getResult());
    }
}
