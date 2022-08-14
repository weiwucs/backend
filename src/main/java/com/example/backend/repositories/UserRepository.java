package com.example.backend.repositories;

import com.example.backend.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.io.Serializable;

public interface UserRepository extends
        PagingAndSortingRepository<User, Long>, //SpringDataJPA provide simple data operation interface
        QueryByExampleExecutor<User>,//SpringDataJPA provide complex query interface
        Serializable {
    public User findUserByUsername(String username);
}
