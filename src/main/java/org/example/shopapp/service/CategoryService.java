package org.example.shopapp.service;

import org.example.shopapp.model.entity.CategoryEntity;
import org.example.shopapp.model.entity.enums.CategoryNameEnum;

public interface CategoryService {
    void databaseInit();

    CategoryEntity findCategory(CategoryNameEnum category);
}
