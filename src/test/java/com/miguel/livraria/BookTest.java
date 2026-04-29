package com.miguel.livraria;

import com.miguel.livraria.domain.Author;
import com.miguel.livraria.domain.Book;
import com.miguel.livraria.domain.Category;
import com.miguel.livraria.domain.Publisher;
import com.miguel.livraria.repository.AuthorRepository;
import com.miguel.livraria.repository.BookRepository;
import com.miguel.livraria.repository.CategoryRepository;
import com.miguel.livraria.repository.PublisherRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.math.BigDecimal;
import java.util.List;

@DataJpaTest
public class BookTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private PublisherRepository publisherRepository;

    @Autowired
    private BookRepository bookRepository;

    private Category category;
    private Author author;
    private Publisher publisher;


    @BeforeEach
    public void setup() {

        author = new Author();
        author.setName("Kyotaka Ayanokoji");
        authorRepository.save(author);

        category = new Category();
        category.setName("Manipulation");
        categoryRepository.save(category);

        publisher = new Publisher();
        publisher.setName("Ayanokoji Corporation");
        publisherRepository.save(publisher);

    }

    @Test
    public void shouldSaveBook() {

        Book book = new Book();
        book.setTitle("The Art of Manipulation I");
        book.setQuantity(10);
        book.setPrice(new BigDecimal("67.67"));
        book.setAuthor(author);
        book.setPublisher(publisher);
        book.setCategory(category);

        Book saved = bookRepository.save(book);

        Assertions.assertNotNull(saved.getId());
        Assertions.assertEquals("The Art of Manipulation I", saved.getTitle());
        Assertions.assertEquals("Kyotaka Ayanokoji", saved.getAuthor().getName());
    }

    @Test
    public void shouldFindBooksByAuthorName() {

        Book book = new Book();
        book.setTitle("O Hobbit");
        book.setQuantity(15);
        book.setPrice(new BigDecimal("66.77"));
        book.setAuthor(author);
        book.setCategory(category);
        book.setPublisher(publisher);

        bookRepository.save(book);

        List<Book> books = bookRepository.findAllByAuthor_Name("Kyotaka Ayanokoji");

        Assertions.assertFalse(books.isEmpty());
        Assertions.assertEquals("Kyotaka Ayanokoji", books.get(0).getAuthor().getName());

    }

    @Test
    public void shouldSaveMultiplesBooks() {

        Book book1 = new Book();
        book1.setTitle("O Hobbit");
        book1.setQuantity(15);
        book1.setPrice(new BigDecimal("66.77"));
        book1.setAuthor(author);
        book1.setCategory(category);
        book1.setPublisher(publisher);

        bookRepository.save(book1);

        Book book2 = new Book();
        book2.setTitle("The Art of Manipulation I");
        book2.setQuantity(10);
        book2.setPrice(new BigDecimal("67.67"));
        book2.setAuthor(author);
        book2.setPublisher(publisher);
        book2.setCategory(category);

        bookRepository.save(book2);

        List<Book> books = bookRepository.findAll();

        Assertions.assertFalse(books.isEmpty());

        books.forEach(book -> System.out.println(book.getTitle()));
    }

}
