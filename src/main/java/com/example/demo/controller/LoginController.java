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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Api(tags = "登录注册控制类")
@Controller
public class LoginController {


    @Autowired
    private UserService userService;

    @GetMapping("/toLogin")
    public String toLogin() {
        return "redirect:";
    }

    @PostMapping(value = "/Login")
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

    @GetMapping("/index")
    public String index() {
        return "view/index";
    }

    @GetMapping("/index/add")
    public String add() {
        return "view/add";
    }

    @GetMapping("/index/update")
    public String update() {
        return "view/update";
    }

    @GetMapping("/logOut")
    public String logOut(Model model) {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            subject.logout();
            model.addAttribute("msg", "已登出");
            return "view/index";
        } else {
            model.addAttribute("msg", "登出失败,请先登录");
            return "view/login";
        }
    }

    @GetMapping("/toSignIn")
    public String toSignIn() {
        return "view/signin";
    }

    @PostMapping("/signIn")
    public String signIn(String username, String password, String phone, String email, Model model) {
        User user = userService.getUserByName(username);
        if (user != null) {
            model.addAttribute("msg", "用户已存在");
            return "view/signin";
        } else {
            String newPassword = new Md5Hash(password, ByteSource.Util.bytes(username)).toHex();
            userService.addUser(new User(0, username, newPassword, phone, email));
            return "view/index";
        }
    }
}
