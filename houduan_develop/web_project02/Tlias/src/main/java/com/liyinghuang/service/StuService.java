package com.liyinghuang.service;

import com.liyinghuang.pojo.PageResult;
import com.liyinghuang.pojo.Student;

public interface StuService {
    PageResult<Student> findStuByCase(String name, Integer degree, Integer clazzId, Integer page, Integer pageSize);
}
