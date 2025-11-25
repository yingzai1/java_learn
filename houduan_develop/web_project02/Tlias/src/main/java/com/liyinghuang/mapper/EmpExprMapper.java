package com.liyinghuang.mapper;

import com.liyinghuang.pojo.Emp;
import com.liyinghuang.pojo.EmpExpr;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

//对员工经历进行操作的接口
@Mapper
public interface EmpExprMapper {
    void add_empExp(List<EmpExpr> empExpList);
//    @Delete("delete from emp_expr where emp_id=#{emp_id}")
//    void delete(@RequestParam(name="id") Integer emp_id);
    void delete(List<Integer> ids);
}
