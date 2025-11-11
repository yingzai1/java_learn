package com.liyinghuang.service;

import com.liyinghuang.mapper.DeptMapper;
import com.liyinghuang.pojo.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service//将其交给IOC容器进行管理
public class DeptServiceImpl implements DeptService{
    @Autowired
    private DeptMapper deptMapper;
    public List<Dept> findAll(){
        return deptMapper.findAll();
    }
    public void delete(Integer id){
        deptMapper.delete(id);
    }

    @Override
    public Object findById(Integer id) {
        Object dept = deptMapper.findById(id);
        return dept;
    }

    public void insert(Dept dept){
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.insert(dept);
    }
    public void update(Dept dept){
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.update(dept);
        }
    }
