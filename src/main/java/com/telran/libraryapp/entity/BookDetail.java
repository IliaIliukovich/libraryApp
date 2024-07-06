package com.telran.libraryapp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class BookDetail {
    @Id
    private Integer id;
    private String publisher;
    private Integer year;
    private String abstractToBook;


}





