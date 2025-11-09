package com.liyinghuang.springbootweb01.Service;

import com.liyinghuang.springbootweb01.Dao.UserDao;
import com.liyinghuang.springbootweb01.Dao.impl.UserDaoImpl;
import com.liyinghuang.springbootweb01.pojo.User;

import java.util.List;

public interface UserService {
    //业务逻辑层，处理具体的业务逻辑
    public List<User> FindAll();
}
