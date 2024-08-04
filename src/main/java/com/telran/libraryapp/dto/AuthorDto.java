package com.telran.libraryapp.dto;

import com.telran.libraryapp.entity.Book;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthorDto {
    private Long id;

    @NotNull
    @Length(max = 45, message = "{validation.author.name}")
    @Pattern(regexp = "[A-Za-z\\s.'-]{1,45}", message = "{validation.author.name}")
    private String name;

    @Length(max = 45, message = "{validation.author.surname}")
    @Pattern(regexp = "[A-Za-z\\s.'-]{0,45}", message = "{validation.author.surname}")
    private String surname;

    @Length(max = 255)
    private String authorInfo;

    private List<BookDto> authorHasBooks;
}