package com.example.backend.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@ApiModel(value = "Food", description = "Food Class")
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, length = 20)
    private Long id;

    @Column(nullable = false, length = 48)
    private String name;

    @Column(length = 48)
    private String nickname;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String ingredient;

    @Column(nullable = false)
    private Boolean eatable;

    private String eatableClass;

    private String taste;

    @Column(nullable = false)
    private Boolean medicinal;

    private String medicineClass;

    private String indication;

    private String prescription;

    private String remarks;
}
