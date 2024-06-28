package com.telran.libraryapp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/books")
public class LibraryController {

    private List<Book> library;

    public LibraryController() {
        library = new ArrayList<>();
        library.add(new Book("Java in action", "Urma R.-G., Fusco M., Mycroft A.", "Java", 2, "1"));
        library.add(new Book("Algorithms", "Robert Sedgewick, Kevin Wayne", "Java", 1, "2"));
        library.add(new Book("Design Patterns", "Erich Gamma, Richard Helm, Ralph Johnson, and John Vlissides", "Java", 4, "3"));
        library.add(new Book("Sherlock Holmes", "Arthur Conan Doyle", "Detectives", 3, "4"));
        library.add(new Book("Harry Potter and the Philosopher's stone", "J. K. Rowling", "Fantasy", 4, "5"));
    }

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

}
