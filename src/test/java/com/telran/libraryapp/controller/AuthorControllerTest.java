package com.telran.libraryapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.telran.libraryapp.dto.AuthorDto;
import com.telran.libraryapp.entity.Author;
import com.telran.libraryapp.service.AuthorService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

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
        AuthorDto author = new AuthorDto();
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
        AuthorDto author = new AuthorDto();
        author.setId(12L);
        author.setName("Jane");
        author.setSurname("Doe");

        Mockito.doNothing().when(service).add(Mockito.any(AuthorDto.class));

        mockMvc.perform(post("/authors")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(author)))
                .andExpect(status().isCreated());

        verify(service).add(Mockito.any(AuthorDto.class));

    }

}