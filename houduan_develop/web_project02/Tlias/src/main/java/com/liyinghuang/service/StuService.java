package com.liyinghuang.service;

import com.liyinghuang.pojo.PageResult;
import com.liyinghuang.pojo.Student;

import java.util.List;

public interface StuService {
    PageResult<Student> findStuByCase(String name, Integer degree, Integer clazzId, Integer page, Integer pageSize);
    void deleteStuById(List<Integer> ids);

    void addStu(Student student);

    Student findStuById(Integer id);

    void updateStu(Student student);

    void violation(Integer id, Integer score);
}
