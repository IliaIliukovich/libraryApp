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
        library.add(new Book("Sherlock Holmes", "Arthur Conan Doyle", "Detective", 3, "4"));
        library.add(new Book("Harry Potter and the Philosopher's stone", "J. K. Rowling", "Fantasy", 4, "5"));
        library.add(new Book("1984", "George Orwell", "Dystopian", 5, "9780451524935"));
        library.add(new Book("To Kill a Mockingbird", "Harper Lee", "Fiction", 7, "9780061120084"));
        library.add(new Book("Pride and Prejudice", "Jane Austen", "Romance", 3, "9781503290563"));
        library.add(new Book("The Great Gatsby", "F. Scott Fitzgerald", "Fiction", 4, "9780743273565"));
        library.add(new Book("Moby Dick", "Herman Melville", "Adventure", 2, "9781503280786"));
        library.add(new Book("War and Peace", "Leo Tolstoy", "Fiction", 6, "9780199232765"));
        library.add(new Book("The Catcher in the Rye", "J.D. Salinger", "Fiction", 5, "9780316769488"));
        library.add(new Book("The Odyssey", "Homer", "Epic", 10, "9780140268867"));
        library.add(new Book("The Divine Comedy", "Dante Alighieri", "Epic", 4, "9780142437223"));
        library.add(new Book("Jane Eyre", "Charlotte Brontë", "Romance", 3, "9781503278196"));
        library.add(new Book("Wuthering Heights", "Emily Brontë", "Fiction", 4, "9780141439556"));
        library.add(new Book("Brave New World", "Aldous Huxley", "Dystopian", 7, "9780060850524"));
        library.add(new Book("Madame Bovary", "Gustave Flaubert", "Fiction", 5, "9780140449129"));
        library.add(new Book("One Hundred Years of Solitude", "Gabriel Garcia Marquez", "Magic Realism", 8, "9780060883287"));
        library.add(new Book("In Search of Lost Time", "Marcel Proust", "Modernist", 2, "9780143039570"));
        library.add(new Book("Ulysses", "James Joyce", "Modernist", 3, "9780199535675"));
        library.add(new Book("Don Quixote", "Miguel de Cervantes", "Adventure", 7, "9780060934347"));
        library.add(new Book("The Iliad", "Homer", "Epic", 5, "9780140275360"));
        library.add(new Book("The Hobbit", "J.R.R. Tolkien", "Fantasy", 9, "9780345339683"));
        library.add(new Book("Fahrenheit 451", "Ray Bradbury", "Dystopian", 4, "9781451673319"));
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
