package com.example.backend.controller;

import com.example.backend.entity.Food;
import com.example.backend.repositories.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@ResponseBody
@RequestMapping(value = "/food")
public class FoodController {

    @Autowired
    private FoodRepository foodRepository;

    @GetMapping
    public List<Food> query(){
        return (List<Food>) foodRepository.findAll();
    }

    @PostMapping
    public List<Food> create(Food food){
        foodRepository.save(food);
        return (List<Food>) foodRepository.findAll();
    }

    @DeleteMapping
    public List<Food> delete(Long id){
        foodRepository.deleteById(id);
        return (List<Food>) foodRepository.findAll();
    }

    @PatchMapping
    public List<Food> update(Food food){
        foodRepository.save(food);
        return (List<Food>) foodRepository.findAll();
    }
}
