package com.telran.libraryapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.telran.libraryapp.dto.BookDto;
import com.telran.libraryapp.entity.Book;
import com.telran.libraryapp.entity.enums.AccessLevel;
import com.telran.libraryapp.repository.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class IntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    BookRepository repository;

    @Test
    public void testAppWorks() throws Exception {
        mockMvc.perform(get("/books/all").contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    public void testPostAndDeleteRequest() throws Exception {
        BookDto book = new BookDto();
        book.setTitle("Title");
        book.setAccessLevel(AccessLevel.OPEN);

        mockMvc.perform(post("/books")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isCreated());

        List<Book> books = repository.findBooksByTitleStartingWithAndAvailableAmountIsGreaterThanEqual("Title", 0);
        Assertions.assertEquals(1, books.size());

        Long id = books.get(0).getId();
        mockMvc.perform(delete("/books?id=" + id)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isAccepted());

        books = repository.findBooksByTitleStartingWithAndAvailableAmountIsGreaterThanEqual("Title", 0);
        Assertions.assertEquals(0, books.size());
    }


}
