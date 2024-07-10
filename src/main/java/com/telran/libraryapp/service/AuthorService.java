package com.telran.libraryapp.service;

import com.telran.libraryapp.entity.Author;
import com.telran.libraryapp.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return authorRepository.findAll();
    }
    public Optional<Author> getNameAuthorByID(Long id) {
        return authorRepository.findById(id);
    }
    public void add(Author author) {
        authorRepository.save(author);
    }
    public boolean updateAuthor(Author author) {
        Optional<Author> authorOptional = authorRepository.findById(author.getId());
        if (authorOptional.isPresent()){
            authorRepository.save(author);
            return true;
        }else{
            authorRepository.save(author);
            return false;
        }
    }

    public void removeAuthor(Long id){
        authorRepository.deleteById(id);
    }
    public List<Author> returnAuthorByNameOrSurname(String name, String surname){
        if (name != null && surname != null) {
          return authorRepository.findAuthorByNameAndSurname(name,surname);
        } else if (name != null) {
            return authorRepository.findAuthorByName(name);
        } else if (surname != null) {
            return authorRepository.findAuthorBySurname(surname);
        } else {
            return authorRepository.findAll();
        }
    }

    public List<Author> getAuthorByRandomWord(String randomWord){
        String formattedWord = '%' + randomWord.trim() + '%';
        return authorRepository.findAuthorByRandomWord(formattedWord);
    }
}
