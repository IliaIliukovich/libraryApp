package com.telran.libraryapp.service;

import com.telran.libraryapp.entity.Book;
import com.telran.libraryapp.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository repository;

    @Autowired
    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public List<Book> getAll() {
        return repository.findAll();
    }

    public List<Book> getBooksByCategory(String category) {
        return repository.findBooksByCategory(category);
    }

    public List<Book> getAllByTitle(String title, Integer amount) {
        if (amount == null) {
            amount = 0;
        }
        return repository.findBooksByTitleStartingWithAndAndAvailableAmountIsGreaterThanEqual(title, amount);
    }

    public void add(Book book) {
        repository.save(book);
    }

    public boolean updateBook(Book book) {
        Optional<Book> optional = repository.findById(book.getIsbn());
        if (optional.isPresent()) {
            repository.save(book);
            return true;
        } else {
            repository.save(book);
            return false;
        }
    }

    public Optional<Book> updateAmountOfBooks(String isbn, Integer amount) {
        Optional<Book> optional = repository.findById(isbn);
        if (optional.isPresent()) {
            Book book = optional.get();
            book.setAvailableAmount(amount);
            Book updatedBook = repository.save(book);
            return Optional.of(updatedBook);
        } else {
            return Optional.empty();
        }
    }

    public void remove(String isbn) {
        repository.deleteById(isbn);
    }
}
