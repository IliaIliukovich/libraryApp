package com.telran.libraryapp.controller;

import com.telran.libraryapp.service.BookService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = BookController.class)
public class BookControllerTest {

    @MockBean
    private BookService service;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void getAll() throws Exception {
        mockMvc.perform(get("/books/all").contentType("application/json"))
                .andExpect(status().isOk());
        Mockito.verify(service).getAll();
    }

}