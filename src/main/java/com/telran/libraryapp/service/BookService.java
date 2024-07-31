package com.telran.libraryapp.service;

import com.telran.libraryapp.entity.Book;
import com.telran.libraryapp.repository.BookRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private static final Logger logger = LogManager.getLogger(BookService.class);

    private final BookRepository repository;

    @Autowired
    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public List<Book> getAll() {
        List<Book> books = repository.findAll();

//        logger.debug("Books retrieved from DB: " + books.stream().map(Book::getTitle).toList());

//        if (logger.isDebugEnabled()) {
//            logger.debug("Books retrieved from DB: {}", books.stream().map(Book::getTitle).toList());
//        }

        logger.debug("Books retrieved from DB: {}", () -> books.stream().map(Book::getTitle).toList());

//        logger.info("info");
//        logger.warn("warn");
//        logger.error("error");
//        logger.fatal("fatal");
        return books;
    }

    public List<Book> getAllByTitle(String title, Integer amount) {
        if (amount == null) {
            amount = 0;
        }
        return repository.findBooksByTitleStartingWithAndAvailableAmountIsGreaterThanEqual(title, amount);
    }

    public Book addOrUpdate(Book book) {
        Book createdOrUpdated = repository.save(book);
        return createdOrUpdated;
    }

    public Book updateBook(Book book) {
        Optional<Book> optional = repository.findById(book.getId());
        if (optional.isPresent()) {
            Book saved = repository.save(book);
            return saved;
        } else {
            return null;
        }
    }

    public Optional<Book> updateAmountOfBooks(Long id, Integer amount) {
        Optional<Book> optional = repository.findById(id);
        if (optional.isPresent()) {
            Book book = optional.get();
            book.setAvailableAmount(amount);
            Book updatedBook = repository.save(book);
            return Optional.of(updatedBook);
        } else {
            return Optional.empty();
        }
    }

    public void updateAmountOfBooksOptimized(Long id, Integer amount) {
        repository.updateAmountOfBooksOptimized(id, amount);
    }

    public void remove(Long id) {
        repository.deleteById(id);
    }
}
