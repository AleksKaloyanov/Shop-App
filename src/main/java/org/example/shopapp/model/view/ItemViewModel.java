package org.example.shopapp.model.view;

import org.example.shopapp.model.entity.CategoryEntity;
import org.example.shopapp.model.entity.enums.CategoryNameEnum;
import org.example.shopapp.model.entity.enums.GenderEnum;

import java.math.BigDecimal;

public class ItemViewModel {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private GenderEnum gender;
    private CategoryEntity category;

    public ItemViewModel() {
    }

    public Long getId() {
        return id;
    }

    public ItemViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ItemViewModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ItemViewModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ItemViewModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public GenderEnum getGender() {
        return gender;
    }

    public ItemViewModel setGender(GenderEnum gender) {
        this.gender = gender;
        return this;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public ItemViewModel setCategory(CategoryEntity category) {
        this.category = category;
        return this;
    }
}
