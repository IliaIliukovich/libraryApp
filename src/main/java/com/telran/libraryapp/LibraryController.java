package com.telran.libraryapp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.telran.libraryapp.Book.library;


@RestController
@RequestMapping("/books")
public class LibraryController {


    @GetMapping
    public String helloMessage() {
        return "Hello from my excellent website!";
    }

    @GetMapping("/all")
    public List<Book> getAll() {
        return library;
    }

    @GetMapping("/{category}")
    public List<Book> getAllByCategory(@PathVariable String category) {
        List<Book> result = library.stream().filter(book -> book.getCategory().equals(category)).toList();
        if (result.isEmpty()) {
            throw new RuntimeException("No books by category " + category + " found. Try search by another category");
        }
        return result;
    }

    @GetMapping("/searchByTitle")
    public List<Book> getAllByTitle(@RequestParam String title, @RequestParam(required = false) Integer amount) {
        return library.stream()
                .filter(book -> book.getTitle().startsWith(title))
                .filter(book -> amount == null || book.getAvailableAmount() >= amount)
                .toList();
    }

    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        library.add(book);
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Book> updateBook(@RequestBody Book book) {
        if (library.contains(book)) {
            int index = library.indexOf(book);
            library.set(index, book);
            return new ResponseEntity<>(book, HttpStatus.OK);
        } else {
            library.add(book);
            return new ResponseEntity<>(book, HttpStatus.CREATED);
        }
    }

    @PatchMapping
    public ResponseEntity<Book> updateAmountOfBooks(@RequestParam String isbn, @RequestParam Integer amount) {
        Optional<Book> book = library.stream().filter(b -> b.getIsbn().equals(isbn)).peek(b -> b.setAvailableAmount(amount)).findAny();
        if (book.isPresent()) {
            return new ResponseEntity<>(book.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping
//    @RequestMapping(value = "/all", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteByIsbn(@RequestParam String isbn) {
        library.removeIf(book -> book.getIsbn().equals(isbn));
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    //    REST запрос на вывод одной книги по ее isbn.
    @GetMapping("/findOneBook")
    public ResponseEntity<Book> findBookByIsbn(@RequestParam String isbn) {
        Optional<Book> firstFindBook = library.stream().filter(book -> book.getIsbn().equals(isbn)).findFirst();
        if (firstFindBook.isPresent()) {
            return new ResponseEntity<>(firstFindBook.get(), HttpStatus.OK);

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //REST запрос на вывод общего числа книг в библиотеке (с учетом копий).
    @GetMapping("/countBooks")
    public int countBooks() {
        int countBooks = library.stream().mapToInt(Book::getAvailableAmount).sum();
        return countBooks;
    }

    //REST запрос на вывод общего числа книг в отдельной категории.
    @GetMapping("/countInOneCategories/{category}")
    public int countInOneCategories(@PathVariable String category) {
        int totalCount = library.stream()
                .filter(book -> book.getCategory().equals(category)).mapToInt(Book::getAvailableAmount).sum();
        return totalCount;
    }

    //REST запрос на заполнение всех пустых полей author значением "Unknown".
    @GetMapping("/updateUnknownAuthors")
    public ResponseEntity<List<Book>> updateUnknownAuthors() {
        library.stream().filter(book -> book.getAuthor().isEmpty()).forEach(book -> book.setAuthor("Unknown"));
        return new ResponseEntity<>(library, HttpStatus.OK);
    }

//REST запрос на удаление из списка книг всех книг, у которых не указан title.
    @DeleteMapping("/deleteWithoutTitle")
    public ResponseEntity<List<Book>> deleteBooksWithoutTitle(){
        library.removeIf(book -> book.getTitle().isEmpty());
        return new ResponseEntity<>(library,HttpStatus.OK);
    }

}