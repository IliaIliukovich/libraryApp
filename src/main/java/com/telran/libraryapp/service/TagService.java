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
        return repository.getAll();
    }

    public Optional<Tag> getById(int id) {
        return repository.getAll().stream()
                .filter(tag -> tag.getTagId() == id)
                .findFirst();
    }

    public Tag createTag(Tag tag) {
        List<Tag> tags = repository.getAll();
        int newId = tags.stream().mapToInt(Tag::getTagId).max().orElse(0) + 1;
        tag.setTagId(newId);
        tags.add(tag);
        return tag;
    }

    public boolean updateTag(Tag tag) {
        List<Tag> tags = repository.getAll();

        for (int i = 0; i < tags.size(); i++) {
            if (tags.get(i).getTagId() == tag.getTagId()) {
                tags.set(i, tag);
                return true;
            }
        }
        return false;
    }

    public void deleteTag(int id) {
        repository.getAll().removeIf(tag -> tag.getTagId() == id);
    }
}





