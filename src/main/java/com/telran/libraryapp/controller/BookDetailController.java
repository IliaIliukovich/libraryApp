package com.telran.libraryapp.controller;

import com.telran.libraryapp.entity.Book;
import com.telran.libraryapp.entity.BookDetail;
import com.telran.libraryapp.entity.Building;
import com.telran.libraryapp.service.BookDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bookDetails")
public class BookDetailController {

    private final BookDetailService service;
    @Autowired
    public BookDetailController(BookDetailService service) {
        this.service = service;
    }
    @GetMapping
    public List<BookDetail> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDetail> getBookDetailById(@PathVariable Integer id) {
        Optional<BookDetail> bookDetail = service.getBDById(id);
        if (bookDetail.isPresent()) {
            return new ResponseEntity<>(bookDetail.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<BookDetail> addBookDetail(@RequestBody BookDetail bookDetail) {
        service.add(bookDetail);
        return new ResponseEntity<>(bookDetail, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<BookDetail> updateBookDetail(@RequestBody BookDetail bookDetail) {
        boolean isUpdated = service.updateBookDetail(bookDetail);
        if (isUpdated) {
            return new ResponseEntity<>(bookDetail, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(bookDetail, HttpStatus.CREATED);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBookDetail(@PathVariable Integer id) {
        service.remove(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
