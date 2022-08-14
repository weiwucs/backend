package com.example.backend.repositories;

import com.example.backend.entity.Food;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface FoodRepository extends
        PagingAndSortingRepository<Food, Long>,
        QueryByExampleExecutor<Food> {
}
