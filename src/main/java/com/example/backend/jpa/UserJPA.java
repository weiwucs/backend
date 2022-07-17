package com.example.backend.jpa;

import com.example.backend.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.io.Serializable;

public interface UserJPA extends
        JpaRepository<UserEntity, Long>, //SpringDataJPA provide simple data operation interface
        JpaSpecificationExecutor<UserEntity>,//SpringDataJPA provide complex query interface
        Serializable {
}
