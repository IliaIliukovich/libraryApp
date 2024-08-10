package com.telran.libraryapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.telran.libraryapp.entity.Book;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {

    private Long id;

    @NotNull(message = "{validation.category.name}")
    @Length(max = 45, message = "{validation.category.name}")
    private String name; // unique in DB

    @Length(max = 255, message = "{validation.category.description}")
    private String description;

    private List<BookDto> books;

}
