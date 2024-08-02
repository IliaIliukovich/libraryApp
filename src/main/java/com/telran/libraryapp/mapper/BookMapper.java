package com.telran.libraryapp.mapper;

import com.telran.libraryapp.dto.BookDto;
import com.telran.libraryapp.entity.Book;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {

    Book dtoToEntity(BookDto bookDto);

    BookDto entityToDto(Book book);

    List<BookDto> entityListToDto(List<Book> books);

}
