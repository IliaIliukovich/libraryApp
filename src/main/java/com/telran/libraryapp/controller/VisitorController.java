package com.telran.libraryapp.controller;

import com.telran.libraryapp.entity.Visitor;
import com.telran.libraryapp.service.VisitorService;
import jakarta.validation.Valid;
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
    public ResponseEntity<List<Visitor>> getAllVisitors() {
        List<Visitor> visitors = visitorService.getAll();
        return new ResponseEntity<>(visitors, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Visitor> getVisitorById(@PathVariable Long id) {
        Optional<Visitor> visitorOpt = visitorService.getVisitorById(id);
        return visitorOpt.map(visitor -> new ResponseEntity<>(visitor, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Visitor> createVisitor(@RequestBody @Valid  Visitor visitor) {
        Visitor created = visitorService.addVisitor(visitor);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Visitor> updateVisitor(@PathVariable Long id, @Valid @RequestBody Visitor visitorDetails) {
        visitorDetails.setId(id);
        boolean updated = visitorService.updateVisitor(visitorDetails);
        if (updated) {
            return new ResponseEntity<>(visitorDetails, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVisitorById(@PathVariable Long id) {
        visitorService.deleteVisitorsById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
