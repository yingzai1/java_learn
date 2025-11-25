package com.liyinghuang.service;

import com.liyinghuang.pojo.Emp;
import com.liyinghuang.pojo.PageResult;

import java.time.LocalDate;
import java.util.List;

//page, , , , ,
public interface EmpService {
    PageResult<Emp> page(String name, Integer gender,
                         LocalDate begin, LocalDate end,
                         Integer page, Integer pageSize);

    void add(Emp emp) throws Exception;

    void delete(List<Integer> ids);

    Emp getInfo(Integer id);

    void update(Emp emp);
}
