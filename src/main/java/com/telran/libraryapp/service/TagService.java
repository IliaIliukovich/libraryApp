package com.telran.libraryapp.service;

import com.telran.libraryapp.entity.Tag;
import com.telran.libraryapp.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TagService {
    private final TagRepository repository;

    @Autowired
    public TagService(TagRepository repository) {
        this.repository = repository;
    }

    public List<Tag> getAll() {
        return repository.findAll();
    }

    public Optional<Tag> getById(int id) {
       return repository.findById(id);
    }



    public Tag createTag(Tag tag) {
        return repository.save(tag);
    }

    public boolean updateTag(Tag tag) {
        Optional<Tag> optional = repository.findById(tag.getTagId());
        if (optional.isPresent()) {
            repository.save(tag);
            return true;
        }else {
            repository.save(tag);
            return false;
        }
    }

    public void deleteTag(int id) {
        repository.deleteById(id);
    }
}





