package com.telran.libraryapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.telran.libraryapp.config.SecurityConfig;
import com.telran.libraryapp.dto.BookDto;
import com.telran.libraryapp.entity.enums.AccessLevel;
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

import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockUser(username = "Test user", roles = {"ADMIN"})
    public void getAll() throws Exception {
        mockMvc.perform(get("/books/all").contentType("application/json"))
                .andExpect(status().isOk());
        Mockito.verify(service).getAll();
    }

    @Test
    @WithMockUser(username = "Test user", roles = {"ADMIN"})
    public void addBook() throws Exception {
        BookDto book = new BookDto();
        book.setId(1L);
        book.setTitle("Title");
        book.setAccessLevel(AccessLevel.OPEN);

        Mockito.when(service.addOrUpdate(eq(book))).thenReturn(book);

        mockMvc.perform(post("/books")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isCreated());
        Mockito.verify(service).addOrUpdate(eq(book));
    }

    @Test
    @WithMockUser(username = "Test user", roles = {"USER"})
    public void addBookWrongUser() throws Exception {
        BookDto book = new BookDto();
        book.setId(1L);
        book.setTitle("Title");
        book.setAccessLevel(AccessLevel.OPEN);

        mockMvc.perform(post("/books")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().is4xxClientError());
    }

}