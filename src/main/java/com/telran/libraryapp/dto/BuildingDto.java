package com.telran.libraryapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BuildingDto {

    private Long id;

    @NotNull(message = "{validation.building.name}")
    @Length(max = 45, message = "{validation.building.name}")
    private String name;

    @NotNull(message = "{validation.building.address}")
    @Length(max = 90, message = "{validation.building.address}")
    private String address;

    private boolean hasReadingRoom;

    List<BookDto> books;

}
