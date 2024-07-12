package com.telran.libraryapp.controller;

import com.telran.libraryapp.entity.Tag;
import com.telran.libraryapp.service.TagService;
import jakarta.validation.Valid;
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

    @GetMapping("/name/{name}")
    public ResponseEntity<Tag> getTagsByName(@PathVariable String name) {
        Optional<Tag> byName = service.getByName(name);
        if (byName.isPresent()) {
            return new ResponseEntity<>(byName.get(), HttpStatus.OK);
        }else  {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tag> getById(@PathVariable Long id) {
        Optional<Tag> optionalTag = service.getById(id);

        return optionalTag.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Tag> addTag(@RequestBody @Valid Tag tag) {
        Tag createdOrUpdated = service.addOrUpdate(tag);
        return new ResponseEntity<>(createdOrUpdated, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tag> updateTag(@RequestBody @Valid Tag tag) {
        Tag updatedTag = service.updateTag(tag);
        return new ResponseEntity<>(tag, updatedTag != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTag(@PathVariable Long id) {
        service.deleteTag(id);
        return ResponseEntity.noContent().build();
    }
}


