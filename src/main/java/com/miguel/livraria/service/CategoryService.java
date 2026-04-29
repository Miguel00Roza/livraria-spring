package com.miguel.livraria.service;

import com.miguel.livraria.domain.Category;
import com.miguel.livraria.exception.BadRequestException;
import com.miguel.livraria.exception.NotFoundException;
import com.miguel.livraria.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Long id) {
        if (id <= 0) {
            throw new BadRequestException("ID must be greater than 0");
        }
        return categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Category not found"));
    }

    public Category create(Category category) {
        if(category.getName() == null || category.getName().isBlank()) {
            throw new BadRequestException("The category name cannot be empty");
        }

        return categoryRepository.save(category);
    }

}
