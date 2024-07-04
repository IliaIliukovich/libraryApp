package com.telran.libraryapp.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Visitor {
    private Integer id;
    private String email;
    private String password;
    private String name;
    private String surname;
    private String role;

    public static List<Visitor> visitorList = new ArrayList<>(List.of(
            new Visitor(11, "john.doe@example.com", "password123", "John", "Doe", "Admin"),
            new Visitor(12, "jane.doe@example.com", "password123", "Jane", "Doe", "User"),
            new Visitor(13, "alice.smith@example.com", "password123", "Alice", "Smith", "User"),
            new Visitor(14, "bob.jones@example.com", "password123", "Bob", "Jones", "Admin"),
            new Visitor(15, "charlie.brown@example.com", "password123", "Charlie", "Brown", "User"),
            new Visitor(16, "david.williams@example.com", "password123", "David", "Williams", "Admin"),
            new Visitor(17, "eve.johnson@example.com", "password123", "Eve", "Johnson", "User"),
            new Visitor(18, "frank.thomas@example.com", "password123", "Frank", "Thomas", "Admin"),
            new Visitor(19, "grace.lee@example.com", "password123", "Grace", "Lee", "User"),
            new Visitor(110, "hank.martin@example.com", "password123", "Hank", "Martin", "Admin"),
            new Visitor(111, "ivy.clark@example.com", "password123", "Ivy", "Clark", "User"),
            new Visitor(112, "jack.rodriguez@example.com", "password123", "Jack", "Rodriguez", "Admin"),
            new Visitor(113, "kate.lewis@example.com", "password123", "Kate", "Lewis", "User"),
            new Visitor(114, "leo.walker@example.com", "password123", "Leo", "Walker", "Admin"),
            new Visitor(115, "mia.hall@example.com", "password123", "Mia", "Hall", "User")
    ));

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Visitor visitor = (Visitor) o;
        return Objects.equals(id, visitor.id) && Objects.equals(email, visitor.email) && Objects.equals(password, visitor.password) && Objects.equals(name, visitor.name) && Objects.equals(surname, visitor.surname) && Objects.equals(role, visitor.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, password, name, surname, role);
    }

}