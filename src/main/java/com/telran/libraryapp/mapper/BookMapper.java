package com.telran.libraryapp.mapper;

import com.telran.libraryapp.dto.BookDto;
import com.telran.libraryapp.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {

    Book dtoToEntity(BookDto bookDto);

    @Mapping(target = "id", source = "book.id")
    @Mapping(target = "categoryId", source = "category.id")
    @Mapping(target = "buildingId", source = "building.id")
    BookDto entityToDto(Book book);

    List<BookDto> entityListToDto(List<Book> books);

}
