package com.example.backend.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, length = 20)
    private Long id;

    @Column(nullable = false, length = 48)
    private String username;

    @Column(length = 48)
    private String nickname;

    @Column(nullable = false, length = 64)
    private String password;
}
