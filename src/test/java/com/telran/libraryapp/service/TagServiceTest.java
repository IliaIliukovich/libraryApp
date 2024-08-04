package com.telran.libraryapp.service;

import com.telran.libraryapp.dto.TagDto;
import com.telran.libraryapp.entity.Tag;
import com.telran.libraryapp.mapper.TagMapper;
import com.telran.libraryapp.repository.TagRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;

public class TagServiceTest {

    private static TagService tagService;
    private static TagRepository repository;
    private static TagMapper tagMapper;

    @BeforeAll
    static void init() {
        repository = Mockito.mock(TagRepository.class);
        tagMapper = Mappers.getMapper(TagMapper.class);
        tagService = new TagService(repository, tagMapper);
    }

    @Test
    public void getAll() {
        tagService.getAll();
        Mockito.verify(repository).findAll();
    }

    @Test
    public void getById() {
        Long id = 1l;
        tagService.getById(id);
        Mockito.verify(repository).findById(id);
    }

    @Test
    public void getByName() {
        String name = "testName";
        tagService.getByName(name);
        Mockito.verify(repository).findTagsByName(name);
    }

    @Test
    public void addOrUpdate() {
        Tag tag = new Tag();
        tag.setName("Test name");

        Mockito.when(repository.save(tag)).thenReturn(tag);
        TagDto resultTag = tagService.addOrUpdate(tagMapper.entityToDto(tag));

        Mockito.verify(repository).save(tag);
        assertEquals(tag.getName(), resultTag.getName());
    }

    @Test
    public void updateTag() {
        Tag newTag = new Tag();
        newTag.setName(" New test name");
        newTag.setTagId(12l);

        Tag oldTag = new Tag();
        oldTag.setName("Old test name");
        oldTag.setTagId(12l);

        Mockito.when(repository.findById(newTag.getTagId())).thenReturn(Optional.of(oldTag));
        Mockito.when(repository.save(newTag)).thenReturn(newTag);

        TagDto result = tagService.updateTag(tagMapper.entityToDto(newTag));

        Mockito.verify(repository).findById(newTag.getTagId());
        Mockito.verify(repository).save(eq(newTag));
        assertEquals(newTag.getName(), result.getName());

        newTag = new Tag();
        newTag.setName(" New test name");
        newTag.setTagId(777l);

        Mockito.when(repository.findById(777l)).thenReturn(Optional.empty());
        result = tagService.updateTag(tagMapper.entityToDto(newTag));
        assertNull(result);
    }

    @Test
    public void deleteTag() {
        Long id = 1l;
        tagService.deleteTag(id);
        Mockito.verify(repository).deleteById(id);
    }
}