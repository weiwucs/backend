package com.example.backend.controller;

import com.example.backend.service.LoginService;
import com.example.backend.utils.LoginParam;
import com.example.backend.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping("/user/login")
    public ResultUtil login(@RequestBody LoginParam loginParam){
        return loginService.login(loginParam);
    }

    @PostMapping("/user/register")
    public ResultUtil register(){
        return ResultUtil.success("register success");
    }
}
