package com.example.demo.service;

import com.example.demo.pojo.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public User getUserByName(String name);
}
