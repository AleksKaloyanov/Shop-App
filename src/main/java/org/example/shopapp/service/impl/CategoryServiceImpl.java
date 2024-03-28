package org.example.shopapp.service.impl;

import org.example.shopapp.model.entity.CategoryEntity;
import org.example.shopapp.model.entity.enums.CategoryNameEnum;
import org.example.shopapp.repository.CategoryRepository;
import org.example.shopapp.service.CategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void databaseInit() {
        if (categoryRepository.count() == 0) {
            for (CategoryNameEnum name : CategoryNameEnum.values()) {

                CategoryEntity category = new CategoryEntity();
                String description = "";

                switch (name) {
                    case DENIM -> description = "This is Denim category";
                    case SHIRT -> description = "This is Shirt category";
                    case JACKET -> description = "This is Jacket category";
                    case SHORTS -> description = "This is Shorts category";
                }

                category
                        .setName(name)
                        .setDescription(description);
                categoryRepository.save(category);
            }
        }
    }

    @Override
    public CategoryEntity findCategory(CategoryNameEnum category) {
        return categoryRepository
                .findByName(category)
                .orElse(null);
    }
}
