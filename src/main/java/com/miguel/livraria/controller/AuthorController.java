package com.miguel.livraria.controller;

import com.miguel.livraria.domain.Author;
import com.miguel.livraria.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping
    public List<Author> getAll() {
        return authorService.getAll();
    }

    @GetMapping
    @RequestMapping("/{id}")
    public Author getAuthorById(@PathVariable long id) {
        return authorService.getAuthorById(id);
    }

    @PostMapping
    public Author create(@RequestBody Author author) {
        return authorService.create(author);
    }

}
