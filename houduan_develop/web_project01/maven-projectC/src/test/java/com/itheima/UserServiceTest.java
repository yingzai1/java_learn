package com.itheima;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("这个程序用来测试UserService类！")
public class UserServiceTest {
    @Test
    public void testgetAge(){
        UserService userService = new UserService();
        Integer age = userService.getAge("350583200302154959");
        System.out.println(age);
    }
    @DisplayName("这个程序用来测试UserService类中的getGender函数")
    @ParameterizedTest
    @ValueSource(strings = {"350583200302154959","350583200302154950"})
    public void testgetGender(String idcard){
        UserService userService = new UserService();
        String gender = userService.getGender(idcard);
        Assertions.assertEquals("男", gender,"性别计算模块逻辑有误！");
    }
}
