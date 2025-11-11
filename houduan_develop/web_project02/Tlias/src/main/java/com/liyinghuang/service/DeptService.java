package com.liyinghuang.service;

import com.liyinghuang.mapper.DeptMapper;
import com.liyinghuang.pojo.Dept;

import java.util.List;

public interface DeptService {
    void insert(Dept dept);
    List<Dept> findAll();
    void delete(Integer id);

    Object findById(Integer id);

    void update(Dept dept);
}
