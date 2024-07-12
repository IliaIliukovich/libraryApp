package com.telran.libraryapp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class BookDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Length(max= 90, message = "{validation.bookDetail.publisher}")
    private String publisher;

    @Pattern(regexp = "^\\d{1,4}( BC)?$", message = "{validation.bookDetail.year}")
    private String year;


    @Length(max = 255, message = "{validation.bookDetail.abstractToBook}")
    private String abstractToBook;


}





