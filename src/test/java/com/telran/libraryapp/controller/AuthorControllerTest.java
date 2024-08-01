package com.telran.libraryapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.telran.libraryapp.entity.Author;
import com.telran.libraryapp.service.AuthorService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthorController.class)
class AuthorControllerTest {

    @MockBean
    private AuthorService service;
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void getAuthors() throws Exception {
        mockMvc.perform(get("/authors").contentType("application/json"))
                .andExpect(status().isOk());
        verify(service).getAll();
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
    void addAuthor() throws Exception {
        Author author = new Author();
        author.setId(12L);
        author.setName("Jane");
        author.setSurname("Done");
        Mockito.doNothing().when(service).add(Mockito.any(Author.class));
        mockMvc.perform(post("/authors")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(author)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(12L))
                .andExpect(jsonPath("$.name").value("Jane"))
                .andExpect(jsonPath("$.surname").value("Done"));

        verify(service).add(Mockito.any(Author.class));

    }

}