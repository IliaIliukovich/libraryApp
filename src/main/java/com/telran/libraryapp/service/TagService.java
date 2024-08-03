package com.telran.libraryapp.service;

import com.telran.libraryapp.dto.TagDto;
import com.telran.libraryapp.entity.Tag;
import com.telran.libraryapp.mapper.TagMapper;
import com.telran.libraryapp.repository.TagRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TagService {

    private static final Logger logger = LogManager.getLogger(TagService.class);
    private final TagRepository repository;
    private final TagMapper tagMapper;

    @Autowired
    public TagService(TagRepository repository, TagMapper tagMapper) {
        this.repository = repository;
        this.tagMapper = tagMapper;
    }

    public List<TagDto> getAll() {
        List<Tag> tags = repository.findAll();
        logger.debug("Tags retrieved from DB: {}", () -> tags.size());
        return tagMapper.entityListToDto(tags);
    }

    public Optional<TagDto> getById(Long id) {
       Optional<Tag> tag = repository.findById(id);
       if (tag.isPresent()) {
         return Optional.of(tagMapper.entityToDto(tag.get()));
       } else {
           return Optional.empty();
       }
    }

    public Optional<TagDto> getByName(String name) {
        Optional<Tag> tag = repository.findTagsByName(name);
        if (tag.isPresent()) {
            return Optional.of(tagMapper.entityToDto(tag.get()));
        } else {
            return Optional.empty();
        }
    }

    public TagDto addOrUpdate(TagDto tagDto) {
        Tag tag = tagMapper.dtoToEntity(tagDto);
        Tag createdOrUpdated = repository.save(tag);
        return tagMapper.entityToDto(createdOrUpdated);
    }

    public TagDto updateTag(TagDto tagDto) {
        Optional<Tag> optional = repository.findById(tagDto.getTagId());
        if (optional.isPresent()) {
            Tag saved = repository.save(tagMapper.dtoToEntity(tagDto));
            return tagMapper.entityToDto(saved);
        }else {
            return null;
        }
    }

    public void deleteTag(Long id) {
        repository.deleteById(id);
    }
}




