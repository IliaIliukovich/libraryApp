package com.telran.libraryapp.entity;

import com.telran.libraryapp.entity.enums.VisitorRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Visitor {

    @Id
    private String email;

    private String password;

    private String name;

    private String surname;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private VisitorRole visitorRole;

    @ManyToMany
    @JoinTable(name = "visitor_took_book",
            joinColumns = @JoinColumn(name = "visitor_email"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private List<Book> takenBooks;

    @ManyToMany
    @JoinTable(
            name = "visitor_reading_room_book",
            joinColumns = @JoinColumn(name = "visitor_email"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private List<Book> readingRoomBooks;



}