package com.telran.libraryapp.mapper;

import com.telran.libraryapp.dto.BookFullInfoDto;
import com.telran.libraryapp.entity.Book;
import com.telran.libraryapp.entity.BookDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookFullInfoMapper {

    @Mapping(target = "id", source = "book.id")
    @Mapping(target = "categoryId", source = "book.category.id")
    @Mapping(target = "buildingId", source = "book.building.id")
    BookFullInfoDto entityToDto(Book book, BookDetail bookDetail);

    Book dtoToBook(BookFullInfoDto bookFullInfoDto);

    BookDetail dtoToBookDetail(BookFullInfoDto bookFullInfoDto);


}
