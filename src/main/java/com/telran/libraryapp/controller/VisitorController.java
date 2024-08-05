package com.telran.libraryapp.controller;

import com.telran.libraryapp.dto.VisitorDto;
import com.telran.libraryapp.entity.Visitor;
import com.telran.libraryapp.service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/visitors")
public class VisitorController {

    private final VisitorService visitorService;

    @Autowired
    public VisitorController(VisitorService visitorService) {
        this.visitorService = visitorService;
    }

    @GetMapping
    public List<VisitorDto> getAllVisitors() {
        return visitorService.getAll();
    }

    @GetMapping("/{email}")
    public ResponseEntity<VisitorDto> getVisitorByEmail(@PathVariable String email) {
        Optional<VisitorDto> optionalVisitorDto = visitorService.getVisitorByEmail(email);
        return optionalVisitorDto.map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<VisitorDto> createVisitor(@RequestBody VisitorDto visitorDto) {
        VisitorDto created = visitorService.addVisitor(visitorDto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{email}")
    public ResponseEntity<VisitorDto> updateVisitor(@PathVariable String email, @RequestBody VisitorDto visitorDetails) {
        visitorDetails.setEmail(email);
        boolean isUpdated = visitorService.updateVisitor(visitorDetails);
        if (isUpdated) {
            return new ResponseEntity<>(visitorDetails, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(visitorDetails, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deleteVisitorByEmail(@PathVariable String email) {
        visitorService.deleteVisitorByEmail(email);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping("/search")
    public List<VisitorDto> getVisitorByName(@RequestParam String name) {
        return visitorService.getVisitorByName(name);
    }

    @DeleteMapping("/roles")
    public ResponseEntity<Void> deleteAllRoles() {
        visitorService.deleteAllRoles();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}