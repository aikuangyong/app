package com.example.demo.config;

import com.example.demo.pojo.User;
import com.example.demo.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        String s = (String) usernamePasswordToken.getPrincipal();
        User user = userService.getUserByName(usernamePasswordToken.getUsername());
        String salt = usernamePasswordToken.getUsername();
        if (user == null) {
            return null;
        }
        return new SimpleAuthenticationInfo(s, user.getPassword(), ByteSource.Util.bytes(salt), this.getName());

    }
}
