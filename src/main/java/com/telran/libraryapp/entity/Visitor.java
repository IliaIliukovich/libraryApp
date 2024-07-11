package com.telran.libraryapp.entity;


import com.telran.libraryapp.entity.enums.AccessLevel;
import com.telran.libraryapp.entity.enums.VisitorRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.List;
import java.util.Objects;

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
    private VisitorRole visitorRole;

    @ManyToMany
    @JoinTable(name = "visitor_took_book",
            joinColumns = @JoinColumn(name = "visitor_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private List<Book> takenBooks;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Visitor visitor = (Visitor) o;
        return Objects.equals(email, visitor.email);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(email);
    }
}