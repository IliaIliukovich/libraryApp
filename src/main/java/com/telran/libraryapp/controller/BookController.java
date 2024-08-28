package com.telran.libraryapp.controller;

import com.telran.libraryapp.dto.BookDto;
import com.telran.libraryapp.dto.BookFullInfoDto;
import com.telran.libraryapp.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
@Validated
@Slf4j
@Tag(name = "Book Controller", description = "Actions with book")
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
    @Operation(summary = "Retrieve all book")
    public List<BookDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/withSort")
    public List<BookDto> getAll(@SortDefault(sort = "title", direction = Sort.Direction.DESC) Sort sort) {
        return service.getAllSorted(sort);
    }

    @GetMapping("/pages")
    public Page<BookDto> getAll(@PageableDefault(size = 10)
                                @SortDefault.SortDefaults({@SortDefault(sort = "title")})
                                    Pageable pageable) {
        return service.getAllByPages(pageable);
    }



    @GetMapping("/searchByTitle")
    @Operation(summary = "Search By Title")
    public List<BookDto> getAllByTitle(@RequestParam String title, @RequestParam(required = false) Integer amount) {
        return service.getAllByTitle(title, amount);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_LIBRARIAN', 'ROLE_ADMIN')")
    public ResponseEntity<BookDto> addBook(@RequestBody @Valid BookDto book) {
        BookDto createdOrUpdated = service.addOrUpdate(book);
        log.info("Book with id = {} created", createdOrUpdated.getId());
        return new ResponseEntity<>(createdOrUpdated, HttpStatus.CREATED);
    }

    @PostMapping("/full")
    public ResponseEntity<BookFullInfoDto> addBook(@RequestBody @Valid BookFullInfoDto book) {
        BookFullInfoDto createdOrUpdated = service.addBookFullInfo(book);
        log.info("Book with id = {} created", createdOrUpdated.getId());
        return new ResponseEntity<>(createdOrUpdated, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<BookDto> updateBook(@RequestBody @Valid BookDto book) {
        BookDto updatedBook = service.updateBook(book);
        return new ResponseEntity<>(updatedBook, updatedBook != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PatchMapping
    public ResponseEntity<BookDto> updateAmountOfBooks(@RequestParam Long id,
                                                    @RequestParam
                                                    @Min(value = 0, message = "{validation.book.availableAmount}")
                                                    Integer amount) {
        Optional<BookDto> book = service.updateAmountOfBooks(id, amount);
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
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> deleteById(@RequestParam Long id) {
        service.remove(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}