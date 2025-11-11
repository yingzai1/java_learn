package com.liyinghuang.springbootweb01.controler;

import cn.hutool.core.io.IoUtil;
import cn.hutool.json.JSONConfig;
import cn.hutool.json.JSONUtil;

import com.liyinghuang.springbootweb01.Dao.UserDao;
import com.liyinghuang.springbootweb01.Dao.impl.UserDaoImpl;
import com.liyinghuang.springbootweb01.Service.UserService;
import com.liyinghuang.springbootweb01.Service.UserServiceImpl.UserServiceImpl;
import com.liyinghuang.springbootweb01.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@RestController
public class UserController{
    @Autowired
    UserService userService;
//该类来接受前端发来的请求，对请求进行处理，并相应数据
    @RequestMapping("/list")
    public List<User> list(){
        List<User> userList=userService.FindAll();
        return userList;
    }

}