package com.ra.model.entity;

import javax.persistence.*;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String categoryName;
    @Column(name = "description")
    private String description;
    @Column(name = "status")
    private Boolean categoryStatus;

    public Category() {
    }

    public Category(Integer id, String categoryName, String description, Boolean categoryStatus) {
        this.id = id;
        this.categoryName = categoryName;
        this.description = description;
        this.categoryStatus = categoryStatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getCategoryStatus() {
        return categoryStatus;
    }

    public void setCategoryStatus(Boolean categoryStatus) {
        this.categoryStatus = categoryStatus;
    }
}
