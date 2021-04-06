package com.example.demo.service;

import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    public User getUserByName(String name) {
        return userMapper.getUserByName(name);
    }

    public String addUser(User user){
        userMapper.addUser(user);
        return "添加成功";
    }

}
