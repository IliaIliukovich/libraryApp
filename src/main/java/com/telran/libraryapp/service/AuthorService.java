package com.telran.libraryapp.service;

import com.telran.libraryapp.dto.AuthorDto;
import com.telran.libraryapp.entity.Author;
import com.telran.libraryapp.mapper.AuthorMapper;
import com.telran.libraryapp.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    @Autowired
    public AuthorService(AuthorRepository authorRepository, AuthorMapper authorMapper) {
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
    }

    public List<AuthorDto> getAll() {
        List<Author> authors = authorRepository.findAll();
        return authorMapper.entityListToDto(authors);
    }

    public Optional<AuthorDto> getNameAuthorByID(Long id) {
        Optional<Author> authorOptional = authorRepository.findById(id);
        return Optional.of(authorMapper.entityToDto(authorOptional.get()));
    }

    public void add(AuthorDto authorDto) {
        Author saved = authorRepository.save(authorMapper.dtoToEntity(authorDto));
    }

    public boolean updateAuthor(AuthorDto authorDto) {
        Optional<Author> authorOptional = authorRepository.findById(authorDto.getId());
        if (authorOptional.isPresent()) {
            authorRepository.save(authorMapper.dtoToEntity(authorDto));
            return true;
        } else {
            authorRepository.save(authorMapper.dtoToEntity(authorDto));
            return false;
        }
    }

    public void removeAuthor(Long id) {
        authorRepository.deleteById(id);

    }

    public List<AuthorDto> returnAuthorByNameOrSurname(String name, String surname) {
        List<Author> result = new ArrayList<>();
        if (name != null && surname != null) {
            result = authorRepository.findAuthorByNameAndSurname(name, surname);
        } else if (name != null) {
            result = authorRepository.findAuthorByName(name);
        } else if (surname != null) {
            result = authorRepository.findAuthorBySurname(surname);
        } else {
            result = authorRepository.findAll();
        }
        return authorMapper.entityListToDto(result);
    }

    public List<AuthorDto> getAuthorByRandomWord(String randomWord) {
        String formattedWord = '%' + randomWord.trim() + '%';
        List<Author> result = authorRepository.findAuthorByRandomWord(formattedWord);
        return authorMapper.entityListToDto(result);
    }
}
