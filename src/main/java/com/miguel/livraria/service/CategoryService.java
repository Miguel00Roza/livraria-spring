package com.miguel.livraria.service;

import com.miguel.livraria.domain.Category;
import com.miguel.livraria.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID must be greater than 0");
        }
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }

    public Category create(@RequestBody Category category) {
        if(category.getName() == null || category.getName().isBlank()) {
            throw new IllegalArgumentException("The category name cannot be empty");
        }

        return categoryRepository.save(category);
    }

}
