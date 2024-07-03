package com.telran.libraryapp.repository;

import com.telran.libraryapp.entity.Author;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AuthorRepository {
    public List<Author> getAllAuthors() {
        return Author.authorList;
    }
}
