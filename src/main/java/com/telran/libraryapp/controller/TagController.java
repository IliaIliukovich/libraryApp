package com.telran.libraryapp.controller;


import com.telran.libraryapp.entity.Tag;
import com.telran.libraryapp.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tags")
public class TagController {
    private final TagService service;

    @Autowired
    public TagController(TagService service) {
        this.service = service;
    }

    @GetMapping
    public List<Tag> getTags() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tag> getById(@PathVariable int id) {
        Optional<Tag> optionalTag = service.getById(id);

        return optionalTag.isPresent() ?
                ResponseEntity.ok(optionalTag.get()) :
                ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Tag> createTag(@RequestBody Tag tag) {
        Tag createdTag = service.createTag(tag);
        return new ResponseEntity<>(createdTag, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tag> updateTag(@RequestBody Tag tag) {
        boolean isUpdated = service.updateTag(tag);

        if (isUpdated) {
            return ResponseEntity.ok(tag);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTag(@PathVariable int id) {
        service.deleteTag(id);
        return ResponseEntity.noContent().build();
    }
}


