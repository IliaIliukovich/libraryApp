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
        return repository.getAll();
    }

    public List<Book> getBooksByCategory(String category) {
        return repository.getAll().stream().filter(book -> book.getCategory().equals(category)).toList();
    }

    public List<Book> getAllByTitle(String title, Integer amount) {
        return repository.getAll().stream()
                .filter(book -> book.getTitle().startsWith(title))
                .filter(book -> amount == null || book.getAvailableAmount() >= amount)
                .toList();
    }

    public void add(Book book) {
        repository.getAll().add(book);
    }

    public boolean updateBook(Book book) {
        List<Book> library = repository.getAll();
        if (library.contains(book)) {
            int index = library.indexOf(book);
            library.set(index, book);
            return true;
        } else {
            library.add(book);
            return false;
        }
    }

    public Optional<Book> updateAmountOfBooks(String isbn, Integer amount) {
        return repository.getAll().stream().filter(b -> b.getIsbn().equals(isbn)).peek(b -> b.setAvailableAmount(amount)).findAny();
    }

    public void remove(String isbn) {
        repository.getAll().removeIf(book -> book.getIsbn().equals(isbn));
    }
}
