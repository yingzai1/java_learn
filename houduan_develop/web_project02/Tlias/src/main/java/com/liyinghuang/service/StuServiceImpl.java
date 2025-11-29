package com.liyinghuang.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.liyinghuang.mapper.StuMapper;
import com.liyinghuang.pojo.PageResult;
import com.liyinghuang.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteStuById(List<Integer> ids) {
        ids.forEach(id -> {stuMapper.deleteStuById(id);});
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addStu(Student student) {
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());
        stuMapper.addStu(student);
    }

    @Override
    public Student findStuById(Integer id) {
        return stuMapper.findStuById(id);
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateStu(Student student) {
        student.setUpdateTime(LocalDateTime.now());
        stuMapper.updateStu(student);
    }

    @Override
    public void violation(Integer id, Integer score) {
        //访问对象当前的违纪次数和违纪分数
        Student stuById = stuMapper.findStuById(id);
        stuMapper.violation(id,score,stuById.getViolationCount()+1,stuById.getViolationScore()+score);
    }
}
