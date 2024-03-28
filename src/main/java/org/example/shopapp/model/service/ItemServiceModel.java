package org.example.shopapp.model.service;

import org.example.shopapp.model.entity.enums.CategoryNameEnum;
import org.example.shopapp.model.entity.enums.GenderEnum;

import java.math.BigDecimal;

public class ItemServiceModel {
    private String name;
    private String description;
    private CategoryNameEnum category;
    private GenderEnum gender;
    private BigDecimal price;

    public ItemServiceModel() {
    }

    public String getName() {
        return name;
    }

    public ItemServiceModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ItemServiceModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public CategoryNameEnum getCategory() {
        return category;
    }

    public ItemServiceModel setCategory(CategoryNameEnum category) {
        this.category = category;
        return this;
    }

    public GenderEnum getGender() {
        return gender;
    }

    public ItemServiceModel setGender(GenderEnum gender) {
        this.gender = gender;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ItemServiceModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }
}
