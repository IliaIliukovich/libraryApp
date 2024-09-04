package com.telran.libraryapp.controller;

import com.telran.libraryapp.config.SecurityConfig;
import com.telran.libraryapp.security.JwtProvider;
import com.telran.libraryapp.service.BookService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = BookController.class)
@Import(SecurityConfig.class)
public class BookControllerTest {

    @MockBean
    private BookService service;

    @MockBean
    private JwtProvider jwtProvider;

    @Autowired
    MockMvc mockMvc;

    @Test
    @WithMockUser(username = "Test user", roles = {"ADMIN"})
    public void getAll() throws Exception {
        mockMvc.perform(get("/books/all").contentType("application/json"))
                .andExpect(status().isOk());
        Mockito.verify(service).getAll();
    }

}