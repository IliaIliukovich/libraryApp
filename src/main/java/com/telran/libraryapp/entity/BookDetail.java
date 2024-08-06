package com.telran.libraryapp.entity;

import jakarta.persistence.*;
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
    @Column(name = "book_detail_id")
    private Long id;

    @Length(max= 90, message = "{validation.bookDetail.publisher}")
    private String publisher;

    @Pattern(regexp = "^\\d{1,4}( BC)?$", message = "{validation.bookDetail.year}")
    private String year;


    @Length(max = 255, message = "{validation.bookDetail.abstractToBook}")
    @Column(name = "abstract")
    private String abstractToBook;


}





