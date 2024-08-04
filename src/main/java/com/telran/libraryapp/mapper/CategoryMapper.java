package com.telran.libraryapp.mapper;

import com.telran.libraryapp.dto.CategoryDto;
import com.telran.libraryapp.entity.Category;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category dtoToEntity(CategoryDto categoryDto);

    CategoryDto entityToDto(Category category);

    List<CategoryDto> entityListToDto(List<Category> categories);

}
