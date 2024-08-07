package com.telran.libraryapp.mapper;

import com.telran.libraryapp.dto.AuthorDto;
import com.telran.libraryapp.entity.Author;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    Author dtoToEntity(AuthorDto authorDto);
    AuthorDto entityToDto(Author author);
    List<AuthorDto> entityListToDto(List<Author> authors);
}
