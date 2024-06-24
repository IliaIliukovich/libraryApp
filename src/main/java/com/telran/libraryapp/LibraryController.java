package com.telran.libraryapp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class LibraryController {

    private List<Book> library;

    public LibraryController() {
        library = new ArrayList<>();
        library.add(new Book("Java in action", "author", "Java", 2, "isbn code"));
        library.add(new Book("Algorithms", "author", "Java", 1, "isbn code"));
        library.add(new Book("Design Patterns", "author", "Java", 4, "isbn code"));
        library.add(new Book("", "author", "Detectives", 3, "isbn code"));
        library.add(new Book("Harry Potter and the Philosopher's stone", "author", "Fantasy", 4, "isbn code"));
    }

    @GetMapping("/home")
    public String helloMessage() {
        return "Hello from my excellent website!";
    }

    @GetMapping("/all")
    public List<Book> getAll() {
        return library;
    }

    @GetMapping("/all/{category}")
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

}
