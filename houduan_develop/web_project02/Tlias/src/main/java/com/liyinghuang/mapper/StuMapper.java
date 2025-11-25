package com.liyinghuang.mapper;

import com.liyinghuang.pojo.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StuMapper {
    List<Student> findStuByCase(String name, Integer degree, Integer clazzId);
}
