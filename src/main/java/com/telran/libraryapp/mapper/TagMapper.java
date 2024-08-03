package com.telran.libraryapp.mapper;

import com.telran.libraryapp.dto.TagDto;
import com.telran.libraryapp.entity.Tag;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TagMapper {
    Tag dtoToEntity(TagDto tagDto);

    TagDto entityToDto(Tag tag);

    List<TagDto> entityListToDto(List<Tag> tags);
}
