package com.telran.libraryapp.mapper;

import com.telran.libraryapp.dto.BuildingDto;
import com.telran.libraryapp.entity.Building;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BuildingMapper {

    Building dtoToEntity(BuildingDto buildingDto);

    BuildingDto entityToDto(Building building);

    List<BuildingDto> entityListToDto(List<Building> buildings);

}
