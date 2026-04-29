package com.miguel.livraria.controller;

import com.miguel.livraria.domain.Book;
import com.miguel.livraria.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    public List<Book> getAll(
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String publisher
            )
    {
        if (author != null) return bookService.getBooksByAuthor(author);
        if (category != null) return bookService.getBooksByCategory(category);
        if (publisher != null) return bookService.getBooksByPublisher(publisher);

        return bookService.getAll();
    }

    @GetMapping
    @RequestMapping("/{id}")
    public Book getBookById(@PathVariable long id) {
        return bookService.getBookById(id);
    }

    @PostMapping
    public Book create(@RequestBody Book book) {
        return bookService.create(book);
    }

}
