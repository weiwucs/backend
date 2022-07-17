package com.example.backend.controller;

import com.example.backend.entity.UserEntity;
import com.example.backend.jpa.UserJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@ResponseBody
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserJPA userJPA;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<UserEntity> list(){
        return userJPA.findAll();
    }

    @RequestMapping(value = "/save", method = RequestMethod.GET)
    public UserEntity save(UserEntity entity){
        return userJPA.save(entity);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public List<UserEntity> delete(Long id){
        userJPA.deleteById(id);
        return userJPA.findAll();
    }
}
