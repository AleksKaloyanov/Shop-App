package org.example.shopapp.model.entity;

import jakarta.persistence.*;
import org.example.shopapp.model.entity.enums.CategoryNameEnum;

@Entity
@Table(name = "categories")
public class CategoryEntity extends BaseEntity {
    @Enumerated(EnumType.STRING)
    @Column(unique = true,nullable = false)
    private CategoryNameEnum name;
    private String description;

    public CategoryEntity() {
    }

    public CategoryNameEnum getName() {
        return name;
    }

    public CategoryEntity setName(CategoryNameEnum name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public CategoryEntity setDescription(String description) {
        this.description = description;
        return this;
    }
}
