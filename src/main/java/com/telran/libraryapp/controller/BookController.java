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

//    @GetMapping("/{categoryId}")
//    public ResponseEntity<List<Book>> getAllByCategory(@PathVariable Integer categoryId) {
//        List<Book> result = service.getBooksByCategory(categoryId);
//        if (result.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(result, HttpStatus.OK);
//    }

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

                                                          Integer amount) {
        service.updateAmountOfBooksOptimized(id, amount);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteById(@RequestParam Long id) {
        service.remove(id);
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