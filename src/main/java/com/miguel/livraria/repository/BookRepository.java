package com.miguel.livraria.repository;

import com.miguel.livraria.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findAllByCategory_Name(String category);
    List<Book> findAllByAuthor_Name(String author);
    List<Book> findAllByPublisher_Name(String publisher);
}
