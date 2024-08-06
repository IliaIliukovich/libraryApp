package com.telran.libraryapp.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class BookDetailDto {


    private Long id;

    @NotNull(message = "{validation.bookDetail.publisher}")
    @Length(max= 90, message = "{validation.bookDetail.publisher}")
    private String publisher;

    @Pattern(regexp = "^\\d{1,4}( BC)?$", message = "{validation.bookDetail.year}")
    private String year;

    @NotNull(message = "{validation.bookDetail.abstractToBook}")
    @Length(max = 255, message = "{validation.bookDetail.abstractToBook}")
    private String abstractToBook;


}