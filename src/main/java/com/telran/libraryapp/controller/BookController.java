package com.telran.libraryapp.controller;

import com.telran.libraryapp.entity.Book;
import com.telran.libraryapp.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;



@RestController
@RequestMapping("/books")
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

    @GetMapping("/{category}")
    public ResponseEntity<List<Book>> getAllByCategory(@PathVariable String category) {
        List<Book> result = service.getBooksByCategory(category);
        if (result.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/searchByTitle")
    public List<Book> getAllByTitle(@RequestParam String title, @RequestParam(required = false) Integer amount) {
        return service.getAllByTitle(title, amount);
    }

    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        service.add(book);
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Book> updateBook(@RequestBody Book book) {
        boolean isUpdated = service.updateBook(book);
        return new ResponseEntity<>(book, isUpdated ? HttpStatus.OK : HttpStatus.CREATED);
    }

    @PatchMapping
    public ResponseEntity<Book> updateAmountOfBooks(@RequestParam String isbn, @RequestParam Integer amount) {
        Optional<Book> book = service.updateAmountOfBooks(isbn, amount);
        if (book.isPresent()) {
            return new ResponseEntity<>(book.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping
//    @RequestMapping(value = "/all", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteByIsbn(@RequestParam String isbn) {
        service.remove(isbn);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

//    //    REST запрос на вывод одной книги по ее isbn.
//    @GetMapping("/findOneBook")
//    public ResponseEntity<Book> findBookByIsbn(@RequestParam String isbn) {
//        Optional<Book> firstFindBook = library.stream().filter(book -> book.getIsbn().equals(isbn)).findFirst();
//        if (firstFindBook.isPresent()) {
//            return new ResponseEntity<>(firstFindBook.get(), HttpStatus.OK);
//
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }

//    //REST запрос на вывод общего числа книг в библиотеке (с учетом копий).
//    @GetMapping("/countBooks")
//    public int countBooks() {
//        return library.stream().mapToInt(Book::getAvailableAmount).sum();
//    }
//
//    //REST запрос на вывод общего числа книг в отдельной категории.
//    @GetMapping("/{category}/count")
//    public int countInOneCategories(@PathVariable String category) {
//        return library.stream()
//                .filter(book -> book.getCategory().equals(category)).mapToInt(Book::getAvailableAmount).sum();
//    }
//
//    //REST запрос на заполнение всех пустых полей author значением "Unknown".
//    @PatchMapping("/updateUnknownAuthors")
//    public ResponseEntity<?> updateUnknownAuthors() {
//        library.stream().filter(book -> book.getAuthor() == null || book.getAuthor().isEmpty()).forEach(book -> book.setAuthor("Unknown"));
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//    //REST запрос на удаление из списка книг всех книг, у которых не указан title.
//    @DeleteMapping("/deleteWithoutTitle")
//    public ResponseEntity<?> deleteBooksWithoutTitle(){
//        library.removeIf(book -> !StringUtils.hasText(book.getTitle()));
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

}