package com.example.demo.config;

import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    UserMapper userMapper;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        User user = userMapper.getUserByName(usernamePasswordToken.getUsername());
        if(! usernamePasswordToken.getUsername().equals(user.getUsername())){
            return null;
        }
        return new SimpleAuthenticationInfo("", user.getPassword(), "");
    }
}
