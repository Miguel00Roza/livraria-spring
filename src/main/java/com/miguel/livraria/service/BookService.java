package com.miguel.livraria.service;

import com.miguel.livraria.domain.Book;
import com.miguel.livraria.exception.BadRequestException;
import com.miguel.livraria.exception.NotFoundException;
import com.miguel.livraria.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) {
        if (id <= 0) throw new BadRequestException("ID must be greater than 0");

        return bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Book not found"));
    }

    public List<Book> getBooksByCategory(String category) {
        if (category == null || category.isBlank()) throw new BadRequestException("Cannot filter by an empty argument");

        return bookRepository.findAllByCategory_Name(category);
    }

    public List<Book> getBooksByPublisher(String publisher) {
        if (publisher == null || publisher.isBlank()) throw new BadRequestException("Cannot filter by an empty argument");

        return bookRepository.findAllByPublisher_Name(publisher);
    }

    public List<Book> getBooksByAuthor(String author) {
        if (author == null || author.isBlank()) throw new BadRequestException("Cannot filter by an empty argument");

        return bookRepository.findAllByAuthor_Name(author);
    }

    public Book create(Book book) {
        if (book.getTitle() == null || book.getTitle().isBlank()) throw new BadRequestException("Title cannot be empty");
        if (book.getQuantity() < 0) throw new BadRequestException("Quantity cannot be negative");
        if (book.getPrice() == null || book.getPrice().doubleValue() <= 0) throw new BadRequestException("Price must be greater than 0");
        if (book.getAuthor() == null) throw new BadRequestException("Author cannot be null");
        if (book.getCategory() == null) throw new BadRequestException("Category cannot be null");
        if (book.getPublisher() == null) throw new BadRequestException("Publisher cannot be null");

        return bookRepository.save(book);
    }

}
