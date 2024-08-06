package com.telran.libraryapp.controller;


import com.telran.libraryapp.dto.BookDetailDto;
import com.telran.libraryapp.entity.BookDetail;
import com.telran.libraryapp.service.BookDetailService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bookDetails")

@Slf4j
public class BookDetailController {

    private final BookDetailService service;

    @Autowired
    public BookDetailController(BookDetailService service) {
        this.service = service;
    }

    @GetMapping
    public List<BookDetailDto> getAll() {
        log.info("Get all book details");
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDetailDto> getBookDetailById(@PathVariable Long id) {
        Optional<BookDetailDto> bookDetail = service.getBDById(id);
        if (bookDetail.isPresent()) {
            log.info("Get book detail by id: {} successfully ", id);
            return new ResponseEntity<>(bookDetail.get(), HttpStatus.OK);
        } else {
            log.info("Get book detail by id: {} not found ", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/addBookDetail")
    public ResponseEntity<BookDetailDto> addBookDetail(@RequestBody @Valid BookDetailDto bookDetail, @RequestParam Long bookID) {
        BookDetailDto savedBookDetail = service.add(bookDetail, bookID);
        log.info("BookDetail with id = {} created", savedBookDetail.getId());
        return new ResponseEntity<>(savedBookDetail, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<BookDetailDto> updateBookDetail(@RequestBody @Valid BookDetailDto bookDetail) {
        boolean isUpdated = service.updateBookDetail(bookDetail);
        if (isUpdated) {
            log.info("BookDetail with id = {} updated", bookDetail.getId());
            log.debug("BookDetail with id = {} updated, publisher = {}, year = {}, abstractToBook = {}", bookDetail.getId(), bookDetail.getPublisher(), bookDetail.getYear(), bookDetail.getAbstractToBook());
            return new ResponseEntity<>(bookDetail, HttpStatus.OK);
        } else {
            log.info("BookDetail with id = {} not found", bookDetail.getId());
            return new ResponseEntity<>(bookDetail, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBookDetail(@PathVariable Long id) {
        service.remove(id);
        log.info("BookDetail with id = {} deleted", id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
