package com.liyinghuang.mapper;

import com.liyinghuang.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {
    @Select("select * from dept order by update_time desc")
    List<Dept> findAll();
    @Delete("delete from dept where id=#{id}")
    void delete(Integer id);
    @Insert("Insert into dept(name, create_time, update_time) values (#{name},#{createTime},#{updateTime})")
    void insert(Dept dept);
    @Select("select * from dept where id=#{id}")
    Dept findById(Integer id);
    @Update("update dept set name=#{name},update_time=#{updateTime} where id=#{id}")
    void update(Dept dept);
}
