package com.telran.libraryapp.service;

import com.telran.libraryapp.entity.Author;
import com.telran.libraryapp.entity.Book;
import com.telran.libraryapp.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Author> getAll(){
        return authorRepository.getAllAuthors();
    }
    public Optional<Author> getNameAuthorByID(int id) {
        return authorRepository.getAllAuthors().stream().filter(author -> author.getId() == id).findFirst();
    }
    public void add(Author author) {
        authorRepository.getAllAuthors().add(author);
    }
    public boolean updateAuthor(Author author) {
        if (authorRepository.getAllAuthors().contains(author)) {
            int index = authorRepository.getAllAuthors().indexOf(author);
            authorRepository.getAllAuthors().set(index, author);
            return true;
        } else {
            authorRepository.getAllAuthors().add(author);
            return false;

        }

    }
    public  void  removeAuthor(int id){
        authorRepository.getAllAuthors().removeIf(author1 -> author1.getId() == id);
    }
    public List<Author> returnAuthorByNameOrSurname(String name, String surname){
        if (name != null && surname != null) {
            return authorRepository.getAllAuthors().stream().filter(author -> author.getSurname().startsWith(surname) && author.getName().startsWith(name)).toList();
        } else if (name != null && surname == null) {
            return authorRepository.getAllAuthors().stream().filter(author -> author.getName().startsWith(name)).toList();
        } else if (name == null && surname != null) {
            return authorRepository.getAllAuthors().stream().filter(author -> author.getSurname().startsWith(surname)).toList();
        } else {
            return authorRepository.getAllAuthors();
        }
    }
    public List<Author> getAuthorByRandomWord(String randomWord){
        List<Author> resultAuthorList = authorRepository.getAllAuthors().stream().filter(author -> author.getName().contains(randomWord) ||
                author.getSurname().contains(randomWord) ||
                author.getAuthorInfo().contains(randomWord)).toList();
        return resultAuthorList;
    }
}
