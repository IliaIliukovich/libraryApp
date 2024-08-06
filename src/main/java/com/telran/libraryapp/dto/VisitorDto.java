package com.telran.libraryapp.dto;

import com.telran.libraryapp.entity.enums.VisitorRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VisitorDto {
    @Email(regexp = "^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$", message = "{validation.visitor.email}")
    @Size(max = 45, message = "{validation.visitor.email}")
    private String email;

    @Size(max = 16, message = "{validation.visitor.password}")
    private String password;

    @Size(max = 45, message = "{validation.visitor.name}")
    private String name;

    @Size(max = 45, message = "{validation.visitor.surname}")
    private String surname;

    private VisitorRole visitorRole;

    private List<BookDto> takenBooks;

    private List<BookDto> readingRoomBooks;
}
