package com.example.demo.controller;

import com.example.demo.pojo.User;
import com.example.demo.service.UserService;
import io.swagger.annotations.Api;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Api(tags = "登录注册控制类")
@RestController
public class LoginController {


    @Autowired
    private UserService userService;

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/Login")
    public String toLogin() {
        return "login";
    }

    @PostMapping(value = "/Login")
    public Map<Object, Object> login(String username, String password) {
        Subject subject = SecurityUtils.getSubject();
        HashMap<Object, Object> hashMap = new HashMap<>();
        if (username != null & password != null) {
            hashMap.put("username", username);
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
            try {
                subject.login(usernamePasswordToken);
                hashMap.put("msg", "登录成功");
                hashMap.put("status", "OK");
                return hashMap;
            } catch (UnknownAccountException e) {
                hashMap.put("msg", "用户名不存在");
                hashMap.put("status", "Error");
                return hashMap;
            } catch (IncorrectCredentialsException e) {
                hashMap.put("msg", "密码错误");
                hashMap.put("status", "Error");
                return hashMap;
            }
        }
        hashMap.put("msg", "用户名及密码不能为空");
        hashMap.put("status", "Error");
        return hashMap;
    }


    @GetMapping("/logOut")
    public Map<Object, Object> logOut() {
        Subject subject = SecurityUtils.getSubject();
        HashMap<Object, Object> hashMap = new HashMap<>();
        if (subject.isAuthenticated()) {
            subject.logout();
            hashMap.put("status", "ok");
            hashMap.put("msg", "已登出");
            return hashMap;
        } else {
            hashMap.put("status", "error");
            hashMap.put("msg", "登出失败,请先登录");
            return hashMap;
        }
    }

    @GetMapping("/SignIn")
    public String toSignIn() {
        return "signin";
    }

    @PostMapping("/signIn")
    public Map<Object, Object> signIn(String username, String password, String phone, String email) {
        HashMap<Object, Object> hashMap = new HashMap<>();
        if (username != null & password != null & phone != null & email != null) {
            User user = userService.getUserByName(username);
            if (user != null) {
                hashMap.put("status", "error");
                hashMap.put("msg", "用户已存在");
                return hashMap;
            } else {
                String newPassword = new Md5Hash(password, ByteSource.Util.bytes(username)).toHex();
                userService.addUser(new User(0, username, newPassword, phone, email));
                hashMap.put("status", "OK");
                hashMap.put("msg", "注册成功");
                return hashMap;
            }
        }
        hashMap.put("status", "Error");
        hashMap.put("msg", "不能留空, 请填写完所有内容");
        return hashMap;
    }
}
