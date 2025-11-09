package com.liyinghuang;

import com.liyinghuang.mapper.UserMapper;
import com.liyinghuang.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SpringbootMybatisQuickstartApplicationTests {
    @Autowired
    UserMapper userMapper;
    @Test
    public void TestFindAll(){
        List<User> userList=userMapper.findAll();
        for (User user:userList) {
            System.out.println(user);
        }
    }
    @Test
    public void TestDeleteById(){
        userMapper.deleteById(5);
    }
    @Test
    public void TestInsert(){
        User user1=new User(1,"yepeng","123456","叶芃",22);
        userMapper.insert(user1);
    }
    @Test
    public void TestUpdate(){
        User user1=new User(1,"huangxiangan","123456","黄祥安",23);
        userMapper.update(user1);
    }
    @Test
    public void testfindByUsernameAndPassword() {
        User huangxiangan = userMapper.findByUsernameAndPassword("huangxiangan", "123456");
        System.out.println(huangxiangan);
    }
}
