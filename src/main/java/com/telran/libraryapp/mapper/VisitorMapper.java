package com.telran.libraryapp.mapper;

import com.telran.libraryapp.dto.VisitorDto;
import com.telran.libraryapp.entity.Visitor;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VisitorMapper {
    Visitor dtoToEntity(VisitorDto visitorDto);

    VisitorDto entityToDto(Visitor visitor);

    List<VisitorDto> entityListToDto(List<Visitor> visitors);
}
