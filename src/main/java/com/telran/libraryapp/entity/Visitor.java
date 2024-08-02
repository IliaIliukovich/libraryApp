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
    @NotNull(message = "validation.visitor.email")
    @Email(regexp = "^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$", message = "validation.visitor.email")
    @Size(max = 45,message = "validation.visitor.email")
    private String email;
    @Size(max = 16,message = "validation.visitor.password")
    private String password;
    @Size(max = 45,message = "validation.visitor.name")
    private String name;
    @Size(max = 45,message = "validation.visitor.name")
    private String surname;
    @Enumerated(EnumType.STRING)
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