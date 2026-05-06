package com.turkcell.spring_cqrs.application.features.category.rule;

import org.springframework.stereotype.Component;

import com.turkcell.spring_cqrs.domain.Category;
import com.turkcell.spring_cqrs.persistence.repository.CategoryRepository;

@Component
public class CategoryBusinessRules {

    private final CategoryRepository categoryRepository;
    public CategoryBusinessRules(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    public void categoryWithSameNameMustNotExist(String name) {
        Category categoryWithSameName = categoryRepository.findByName(name).orElse(null);
        if (categoryWithSameName != null) {
            throw new RuntimeException("Category with the same name already exists.");
        }
    }

}
