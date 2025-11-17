package com.liyinghuang.service;

import com.liyinghuang.pojo.Emp;
import com.liyinghuang.pojo.PageResult;

import java.time.LocalDate;
//page, , , , ,
public interface EmpService {
    PageResult<Emp> page(String name, Integer gender,
                         LocalDate begin, LocalDate end,
                         Integer page, Integer pageSize);

    void add(Emp emp) throws Exception;
}
