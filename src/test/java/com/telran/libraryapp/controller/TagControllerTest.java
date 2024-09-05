package com.telran.libraryapp.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import com.telran.libraryapp.config.SecurityConfig;
import com.telran.libraryapp.dto.TagDto;
import com.telran.libraryapp.entity.Tag;
import com.telran.libraryapp.security.JwtProvider;
import com.telran.libraryapp.service.TagService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(TagController.class)
@Import(SecurityConfig.class)
public class TagControllerTest {

    @MockBean
    private TagService service;

    @MockBean
    private JwtProvider jwtProvider;

    @Autowired
    private MockMvc mockMvc;


    @Test
    @WithMockUser(username = "Test user", roles = {"ADMIN"})
    public void getTagsByName() throws Exception {
        TagDto tagDto = new TagDto();
        tagDto.setName("exampleTag");

        when(service.getByName("exampleTag")).thenReturn(Optional.of(tagDto));

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