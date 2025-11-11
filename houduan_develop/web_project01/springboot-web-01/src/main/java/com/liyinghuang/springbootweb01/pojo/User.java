package com.liyinghuang.springbootweb01.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data//声明这是一个数据类，则不需要设置get（）和set（）方法
@NoArgsConstructor//无参构造器
@AllArgsConstructor//有参构造器
public class User {
    private Integer id;//这些名字都不能随便取的，要与数据库字段名一致。
    private String username;
    private String password;
    private String name;
    private Integer age;
    private LocalDateTime updateTime;
}
