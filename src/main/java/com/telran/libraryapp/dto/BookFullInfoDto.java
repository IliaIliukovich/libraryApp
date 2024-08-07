package com.telran.libraryapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.telran.libraryapp.entity.enums.AccessLevel;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookFullInfoDto {

    private Long id;

    @NotNull(message = "{validation.book.title}")
    @Length(max = 90, message = "{validation.book.title}")
    private String title;

    @Min(value = 0, message = "{validation.book.availableAmount}")
    private int availableAmount;

    @Pattern(regexp = "[\\d]{13}", message = "{validation.book.isbn}")
    private String isbn;

    private AccessLevel accessLevel;

    @JsonIgnore
    private CategoryDto category;

    @JsonIgnore
    private BookDetailDto bookDetail;

    @JsonIgnore
    private BuildingDto building;

    @Length(max= 90, message = "{validation.bookDetail.publisher}")
    private String publisher;

    @Pattern(regexp = "^\\d{1,4}( BC)?$", message = "{validation.bookDetail.year}")
    private String year;

    @Length(max = 255, message = "{validation.bookDetail.abstractToBook}")
    private String abstractToBook;

}
