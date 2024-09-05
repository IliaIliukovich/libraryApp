package com.telran.libraryapp.controller;

import com.telran.libraryapp.config.SecurityConfig;
import com.telran.libraryapp.dto.BookDetailDto;
import com.telran.libraryapp.entity.BookDetail;
import com.telran.libraryapp.security.JwtProvider;
import com.telran.libraryapp.service.BookDetailService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookDetailController.class)
@Import(SecurityConfig.class)
class BookDetailControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private JwtProvider jwtProvider;
    @MockBean
    private BookDetailService bookDetailService;

    @Test
    @WithMockUser(username = "Test user", roles = {"ADMIN"})
    public void getAll() throws Exception {
        mockMvc.perform(get("/bookDetails").contentType("application/json"))
                .andExpect(status().isOk());
        Mockito.verify(bookDetailService).getAll();

    }

    @Test
    @WithMockUser(username = "Test user", roles = {"ADMIN"})
    public void getBookDetailById() throws Exception {
        when(bookDetailService.getBDById(anyLong()))
                .thenReturn(Optional.of(new BookDetailDto(22L, "Addison-Wesley", "2011", "A comprehensive introduction to algorithms")));
        this.mockMvc.perform(get("/bookDetails/{id}", 1))
                .andExpect(status().isOk());
        Mockito.verify(bookDetailService, times(1)).getBDById(anyLong());
    }

    @Test
    @WithMockUser(username = "Test user", roles = {"ADMIN"})
    public void getBookDetailByIdNotFound() throws Exception {
        when(bookDetailService.getBDById(anyLong()))
                .thenReturn(Optional.empty());
        this.mockMvc.perform(get("/bookDetails/{id}", 1))
                .andExpect(status().isNotFound());
        Mockito.verify(bookDetailService, times(1)).getBDById(anyLong());
    }

}