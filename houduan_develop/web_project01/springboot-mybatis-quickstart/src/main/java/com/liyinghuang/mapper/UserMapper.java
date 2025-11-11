package com.liyinghuang.mapper;

import com.liyinghuang.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper //应用程序在运行时，会自动地为该接口创建一个实现类对象（代理对象），并且会自动将该代理对象存入IOC容器中
public interface UserMapper {
   // @Select("select * from user")
    public List<User> findAll();
    //根据id删除用户
    @Delete("delete from user where id=#{id}")
    public void deleteById(Integer id);
    @Insert("insert into user(username, password, name, age) values (#{username},#{password},#{name},#{age})")
    //values(#{username},#{password},#{name},#{age})括号里面填的都是User的属性名。
    public void insert(User user);
    @Update("update user set username=#{username},password=#{password},name=#{name},age=#{age} where id=#{id}")
    public void update(User user);
    //使用username和password查找用户
    @Select("select * from user where username=#{username} and password=#{password}")
    public User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
}
