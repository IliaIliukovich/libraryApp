package com.telran.libraryapp.mapper;

import com.telran.libraryapp.dto.BookDto;
import com.telran.libraryapp.dto.CategoryDto;
import com.telran.libraryapp.entity.Book;
import com.telran.libraryapp.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class CategoryMapper {

    public abstract Category dtoToEntity(CategoryDto categoryDto);

    public abstract CategoryDto entityToDto(Category category);

    public BookDto bookToBookDto(Book book) {
        return Mappers.getMapper(BookMapper.class).entityToDto(book);
    }

    public abstract List<CategoryDto> entityListToDto(List<Category> categories);

}
