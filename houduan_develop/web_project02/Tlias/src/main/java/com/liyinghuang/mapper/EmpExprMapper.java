package com.liyinghuang.mapper;

import com.liyinghuang.pojo.Emp;
import com.liyinghuang.pojo.EmpExpr;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

//对员工经历进行操作的接口
@Mapper
public interface EmpExprMapper {
    void add_empExp(List<EmpExpr> empExpList);
}
