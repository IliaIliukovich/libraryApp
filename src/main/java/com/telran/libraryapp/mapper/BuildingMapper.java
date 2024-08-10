package com.telran.libraryapp.mapper;

import com.telran.libraryapp.dto.BookDto;
import com.telran.libraryapp.dto.BuildingDto;
import com.telran.libraryapp.entity.Book;
import com.telran.libraryapp.entity.Building;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class BuildingMapper {

    public abstract Building dtoToEntity(BuildingDto buildingDto);

    public abstract BuildingDto entityToDto(Building building);

    public BookDto bookToBookDto(Book book) {
        return Mappers.getMapper(BookMapper.class).entityToDto(book);
    }

    public abstract List<BuildingDto> entityListToDto(List<Building> buildings);

}
