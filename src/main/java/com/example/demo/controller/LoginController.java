package com.example.demo.controller;

import com.example.demo.pojo.User;
import com.example.demo.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class LoginController {


    @Autowired
    private UserService userService;

    @RequestMapping("/toLogin")
    public String toLogin() {
        return "view/login";
    }

    @RequestMapping(value = "/Login", method = RequestMethod.POST)
    public String login(String username, String password, Model model) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
        try {
            subject.login(usernamePasswordToken);
            return "view/index";
        } catch (UnknownAccountException e) {
            model.addAttribute("msg", "用户名不存在");
            return "view/login";
        } catch (IncorrectCredentialsException e) {
            model.addAttribute("msg", "密码错误");
            return "view/login";
        }
    }

    @RequestMapping("/index")
    public String index() {
        return "view/index";
    }

    @RequestMapping("/index/add")
    public String add() {
        return "view/add";
    }

    @RequestMapping("/index/update")
    public String update() {
        return "view/update";
    }

    @RequestMapping("/logOut")
    public String logOut(Model model) {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            subject.logout();
            model.addAttribute("msg", "已登出");
            return "view/index";
        } else {
            model.addAttribute("msg", "登出失败");
            return "view/login";
        }
    }

    @RequestMapping("/toSignIn")
    public String toSignIn() {
        return "view/signin";
    }

    @RequestMapping("/signIn")
    public String signIn(String username, String password, String phone, String email, Model model) {
        User user = userService.getUserByName(username);
        if (user != null){
            model.addAttribute("msg", "用户已存在");
            return "view/signin";
        }else{
            String newPassword = new Md5Hash(password).toHex();
            userService.addUser(new User(0, username, newPassword, phone, email));
            return "view/index";
        }
    }
}
