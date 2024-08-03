package com.telran.libraryapp.controller;

import com.telran.libraryapp.dto.TagDto;
import com.telran.libraryapp.service.TagService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tags")
@Slf4j
@Tag(name = "Tag Controller", description = "Action with Tag")
public class TagController {
    private final TagService service;

    @Autowired
    public TagController(TagService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Retrieve all tag")
    public List<TagDto> getTags() {
        return service.getAll();
    }

    @GetMapping("/name/{name}")
    @Operation(summary = "Retrieve a tag by name")
    public ResponseEntity<TagDto> getTagsByName(@PathVariable String name) {
        Optional<TagDto> byName = service.getByName(name);
        if (byName.isPresent()) {
            return new ResponseEntity<>(byName.get(), HttpStatus.OK);
        }else  {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retrieve a tag by ID")
    public ResponseEntity<TagDto> getById(@PathVariable Long id) {
        Optional<TagDto> optionalTag = service.getById(id);

        return optionalTag.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Add a new tag or update an existing one")
    public ResponseEntity<TagDto> addTag(@RequestBody @Valid TagDto tag) {
        TagDto createdOrUpdated = service.addOrUpdate(tag);
        log.info("Tag with name = {} created", createdOrUpdated.getName());
        return new ResponseEntity<>(createdOrUpdated, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing tag")
    public ResponseEntity<TagDto> updateTag(@RequestBody @Valid TagDto tag) {
        TagDto updatedTag = service.updateTag(tag);
        return new ResponseEntity<>(updatedTag, updatedTag != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a tag by ID")
    public ResponseEntity<Void> deleteTag(@PathVariable Long id) {
        service.deleteTag(id);
        return ResponseEntity.noContent().build();
    }
}


