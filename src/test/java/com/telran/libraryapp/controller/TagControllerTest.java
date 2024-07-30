package com.telran.libraryapp.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import com.telran.libraryapp.entity.Tag;
import com.telran.libraryapp.service.TagService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(TagController.class)
public class TagControllerTest {

    @MockBean
    private TagService service;

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void getTagsByName() throws Exception {
        Tag tag = new Tag();
        tag.setName("exampleTag");

        when(service.getByName("exampleTag")).thenReturn(Optional.of(tag));

        mockMvc.perform(get("/tags/name/exampleTag")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"name\":\"exampleTag\"}"));

        when(service.getByName("nonExistentTag")).thenReturn(Optional.empty());

        mockMvc.perform(get("/tags/name/nonExistentTag")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}