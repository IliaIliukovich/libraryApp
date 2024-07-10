package com.telran.libraryapp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class BookDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String publisher;
    private String year;
    private String abstractToBook;


}





