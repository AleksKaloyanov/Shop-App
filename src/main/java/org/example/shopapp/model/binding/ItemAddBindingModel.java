package org.example.shopapp.model.binding;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.example.shopapp.model.entity.enums.CategoryNameEnum;
import org.example.shopapp.model.entity.enums.GenderEnum;

import java.math.BigDecimal;

public class ItemAddBindingModel {
    @Size(min = 2)
    @Column(unique = true, nullable = false)
    private String name;
    @Size(min = 3)
    private String description;
    private CategoryNameEnum category;
    private GenderEnum gender;
    @Positive
    private BigDecimal price;

    public ItemAddBindingModel() {
    }

    public String getName() {
        return name;
    }

    public ItemAddBindingModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ItemAddBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public CategoryNameEnum getCategory() {
        return category;
    }

    public ItemAddBindingModel setCategory(CategoryNameEnum category) {
        this.category = category;
        return this;
    }

    public GenderEnum getGender() {
        return gender;
    }

    public ItemAddBindingModel setGender(GenderEnum gender) {
        this.gender = gender;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ItemAddBindingModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }
}
