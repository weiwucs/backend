package com.example.backend.service;

import com.example.backend.utils.LoginParam;
import com.example.backend.utils.ResultUtil;
import org.springframework.stereotype.Component;

@Component
public interface LoginService{
    ResultUtil login(LoginParam loginParam);
}
