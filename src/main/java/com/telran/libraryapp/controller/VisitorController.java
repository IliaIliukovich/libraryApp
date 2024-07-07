package com.telran.libraryapp.controller;

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
    public List<Visitor> getAllVisitors() {
        return visitorService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Visitor> getVisitorById(@PathVariable Integer id) {
        return visitorService.getVisitorById(id);
    }

    @PostMapping
    public Visitor createVisitor(@RequestBody Visitor visitor) {
        visitorService.addVisitor(visitor);
        return visitor;
    }

    @PutMapping("/{id}")
    public boolean updateVisitor(@PathVariable Integer id, @RequestBody Visitor visitorDetails) { // TODO
        visitorDetails.setId(id);
        return visitorService.updateVisitor(visitorDetails);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteVisitorById(@PathVariable Integer id) {
        visitorService.deleteVisitorsById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping("/search")
    public List<Visitor> getVisitorByName(@RequestParam String name) {
        return visitorService.getVisitorByName(name);
    }

    @DeleteMapping("/roles")
    public void deleteAllRoles() {
        visitorService.deleteAllRoles();
    }
}