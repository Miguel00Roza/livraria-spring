package com.miguel.livraria.controller;

import com.miguel.livraria.domain.Category;
import com.miguel.livraria.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public List<Category> getAll() {
        return categoryService.getAll();
    }

    @GetMapping
    @RequestMapping("/{id}")
    public Category getCategoryById(@PathVariable("id") long id) {
        return categoryService.getCategoryById(id);
    }

    @PostMapping
    public Category create(@RequestBody Category category) {
        return categoryService.create(category);
    }
}
