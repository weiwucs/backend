package com.example.backend.service.impl;

import com.example.backend.entity.User;
import com.example.backend.entity.UserLogin;
import com.example.backend.service.LoginService;
import com.example.backend.utils.JwtTokenUtil;
import com.example.backend.utils.LoginParam;
import com.example.backend.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class LoginServiceImpl implements LoginService {

    // define authenticate method, manager all provider which can be authenticated.
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public ResultUtil login(LoginParam loginParam) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginParam.getUsername(), loginParam.getPassword());
        //current user requesting system, encapsulate user info. for authentication
        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        if (Objects.isNull(authentication)){
            throw new RuntimeException("Login fail");
        }

        UserLogin userLogin = (UserLogin) authentication.getPrincipal();
        User user = userLogin.getUser();
        String token = JwtTokenUtil.createTokenByUser(user);
        return ResultUtil.success("Login success", token);
    }
}
