package com.telran.libraryapp.service;

import com.telran.libraryapp.entity.Tag;
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

    @Autowired
    public TagService(TagRepository repository) {
        this.repository = repository;
    }

    public List<Tag> getAll() {
        List<Tag> tags = repository.findAll();

        logger.debug("Tags retrieved from DB: {}", () -> tags.size());
        return tags;
    }

    public Optional<Tag> getById(Long id) {
        return repository.findById(id);
    }

    public Optional<Tag> getByName(String name) {
        return repository.findTagsByName(name);
    }

    public Tag addOrUpdate(Tag tag) {
        Tag createdOrUpdated = repository.save(tag);
        return createdOrUpdated;
    }

    public Tag updateTag(Tag tag) {
        Optional<Tag> optional = repository.findById(tag.getTagId());
        if (optional.isPresent()) {
            Tag saved = repository.save(tag);
            return saved;
        }else {
            repository.save(tag);
            return null;
        }
    }

    public void deleteTag(Long id) {
        repository.deleteById(id);
    }
}




