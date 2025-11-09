package com.liyinghuang.springbootweb01.Dao.impl;

import cn.hutool.core.io.IoUtil;
import com.liyinghuang.springbootweb01.Dao.UserDao;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
@Component//说明将该类交给容器管理
public class UserDaoImpl implements UserDao {
    public List<String> findAll(){
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("user.txt");
        ArrayList<String> lines = IoUtil.readLines(in, StandardCharsets.UTF_8, new ArrayList<>());
        return lines;
    }
}
