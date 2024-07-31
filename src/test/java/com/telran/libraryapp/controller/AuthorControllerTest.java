package com.telran.libraryapp.controller;

import com.telran.libraryapp.entity.Author;
import com.telran.libraryapp.service.AuthorService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthorController.class)
class AuthorControllerTest {

    @MockBean
    private AuthorService service;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void getAuthors() throws Exception {
        mockMvc.perform(get("/authors").contentType("application/json"))
                .andExpect(status().isOk());
        Mockito.verify(service).getAll();
    }

    @Test
    void getAuthorById() throws Exception {
        Author author = new Author();
        author.setId(15L);
        when(service.getNameAuthorByID(15L)).thenReturn(Optional.of(author));
        mockMvc.perform(get("/authors/15").contentType("application/json"))
                .andExpect(status().isOk());

        when(service.getNameAuthorByID(178L)).thenReturn(Optional.empty());
        mockMvc.perform(get("/authors/178").contentType("application/json"))
                .andExpect(status().isNotFound());

    }

    @Test
    void addAuthor() {


    }

    @Test
    void updateAuthor() {
    }

    @Test
    void deleteAuthor() {
    }

    @Test
    void getAuthorByNameOrSurname() {
    }

    @Test
    void getAuthorByRandomWord() {
    }
}