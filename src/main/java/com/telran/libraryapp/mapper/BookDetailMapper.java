package com.telran.libraryapp.mapper;

import com.telran.libraryapp.dto.BookDetailDto;
import com.telran.libraryapp.entity.BookDetail;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookDetailMapper {

    BookDetail dtoToEntity (BookDetailDto bookDetailDto);
    BookDetailDto entityToDto (BookDetail bookDetail);
    List<BookDetailDto> entityListToDto (List<BookDetail> bookDetailList);

}
