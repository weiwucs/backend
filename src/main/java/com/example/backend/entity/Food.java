package com.example.backend.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;

import javax.persistence.*;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public Boolean getEatable() {
        return eatable;
    }

    public void setEatable(Boolean eatable) {
        this.eatable = eatable;
    }

    public String getEatableClass() {
        return eatableClass;
    }

    public void setEatableClass(String eatableClass) {
        this.eatableClass = eatableClass;
    }

    public String getTaste() {
        return taste;
    }

    public void setTaste(String taste) {
        this.taste = taste;
    }

    public Boolean getMedicinal() {
        return medicinal;
    }

    public void setMedicinal(Boolean medicinal) {
        this.medicinal = medicinal;
    }

    public String getMedicineClass() {
        return medicineClass;
    }

    public void setMedicineClass(String medicineClass) {
        this.medicineClass = medicineClass;
    }

    public String getIndication() {
        return indication;
    }

    public void setIndication(String indication) {
        this.indication = indication;
    }

    public String getPrescription() {
        return prescription;
    }

    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
