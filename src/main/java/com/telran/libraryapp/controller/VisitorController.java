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

    @GetMapping("/{email}")
    public Optional<Visitor> getVisitorByEmail(@PathVariable String email) {
        return visitorService.getVisitorByEmail(email);
    }

    @PostMapping
    public Visitor createVisitor(@RequestBody Visitor visitor) {
        Visitor created = visitorService.addVisitor(visitor);
        return created;
    }

    @PutMapping("/{email}")
    public boolean updateVisitor(@PathVariable String email, @RequestBody Visitor visitorDetails) { // TODO
        visitorDetails.setEmail(email);
        return visitorService.updateVisitor(visitorDetails);
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<?> deleteVisitorByEmail(@PathVariable String email) {
        visitorService.deleteVisitorsByEmail(email);
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