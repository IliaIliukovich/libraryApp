package com.telran.libraryapp.controller;

import com.telran.libraryapp.entity.Book;
import com.telran.libraryapp.service.BookService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
@Validated
public class BookController {
    
    private final BookService service;

    @Autowired
    public BookController(BookService service) {
        this.service = service;
    }

    @GetMapping
    public String helloMessage() {
        return "Hello from my excellent website!";
    }

    @GetMapping("/all")
    public List<Book> getAll() {
        return service.getAll();
    }

    @GetMapping("/searchByTitle")
    public List<Book> getAllByTitle(@RequestParam String title, @RequestParam(required = false) Integer amount) {
        return service.getAllByTitle(title, amount);
    }

    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody @Valid Book book) {
        Book createdOrUpdated = service.addOrUpdate(book);
        return new ResponseEntity<>(createdOrUpdated, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Book> updateBook(@RequestBody @Valid Book book) {
        Book updatedBook = service.updateBook(book);
        return new ResponseEntity<>(book, updatedBook != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PatchMapping
    public ResponseEntity<Book> updateAmountOfBooks(@RequestParam Long id,
                                                    @RequestParam
                                                    @Min(value = 0, message = "{validation.book.availableAmount}")
                                                    Integer amount) {
        Optional<Book> book = service.updateAmountOfBooks(id, amount);
        if (book.isPresent()) {
            return new ResponseEntity<>(book.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/updateAmountOfBooksOptimized")
    public ResponseEntity<?> updateAmountOfBooksOptimized(@RequestParam Long id,
                                                          @RequestParam
                                                          @Min(value = 0, message = "{validation.book.availableAmount}")
                                                          Integer amount) {
        service.updateAmountOfBooksOptimized(id, amount);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteById(@RequestParam Long id) {
        service.remove(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}