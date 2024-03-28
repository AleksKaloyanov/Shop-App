package org.example.shopapp.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.example.shopapp.model.entity.enums.GenderEnum;

import java.math.BigDecimal;

@Entity
@Table(name = "items")
public class ItemEntity extends BaseEntity {
    @Size(min = 2)
    @Column(unique = true, nullable = false)
    private String name;
    @Size(min = 3)
    private String description;
    @Positive
    private BigDecimal price;
    @ManyToOne
    private CategoryEntity category;
    @Enumerated(EnumType.STRING)
    private GenderEnum gender;

    public ItemEntity() {
    }

    public String getName() {
        return name;
    }

    public ItemEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ItemEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ItemEntity setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public ItemEntity setCategory(CategoryEntity category) {
        this.category = category;
        return this;
    }

    public GenderEnum getGender() {
        return gender;
    }

    public ItemEntity setGender(GenderEnum gender) {
        this.gender = gender;
        return this;
    }
}
