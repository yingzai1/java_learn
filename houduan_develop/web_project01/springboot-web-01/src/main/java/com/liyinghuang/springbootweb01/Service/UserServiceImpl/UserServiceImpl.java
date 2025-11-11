package com.liyinghuang.springbootweb01.Service.UserServiceImpl;

import com.liyinghuang.springbootweb01.Dao.UserDao;
import com.liyinghuang.springbootweb01.Dao.impl.UserDaoImpl;
import com.liyinghuang.springbootweb01.Service.UserService;
import com.liyinghuang.springbootweb01.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
@Component
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;
    public List<User> FindAll(){
        List<String> lines = userDao.findAll();
        List<User> userList = lines.stream().map(line -> {
            String[] parts = line.split(",");
            Integer id = Integer.parseInt(parts[0]);
            String username = parts[1];
            String password = parts[2];
            String name = parts[3];
            Integer age = Integer.parseInt(parts[4]);
            LocalDateTime updateTime = LocalDateTime.parse(parts[5], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            return new User(id, username, password, name, age, updateTime);
        }).collect(Collectors.toList());
        return userList;
    }

}
