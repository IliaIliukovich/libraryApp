package com.telran.libraryapp.repository;

import com.telran.libraryapp.entity.Book;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookRepository {

    public List<Book> getAll() {
        return Book.library;
    }

}
