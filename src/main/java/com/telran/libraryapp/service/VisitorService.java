package com.telran.libraryapp.service;


import com.telran.libraryapp.entity.Book;
import com.telran.libraryapp.entity.Visitor;
import com.telran.libraryapp.repository.VisitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VisitorService {

    private final VisitorRepository visitorRepository;

    @Autowired
    public VisitorService(VisitorRepository visitorRepository) {
        this.visitorRepository = visitorRepository;

    }

    public List<Visitor> getAll() {
        return visitorRepository.findAll();
    }

    public Optional<Visitor> getVisitorById(String id) {
        return visitorRepository.findById(id);
    }

    public void addVisitor(Visitor visitor) {
        visitorRepository.save(visitor);
    }

    public boolean updateVisitor(Visitor visitor) { // TODO
        Optional<Visitor> optional = visitorRepository.findById(visitor.getEmail());
        if (optional.isPresent()) {
            visitorRepository.save(visitor);
            return true;
        } else {
            visitorRepository.save(visitor);
            return false;
        }
    }

    public void deleteVisitorsById(String id) {
        visitorRepository.deleteById(id);
    }

    public List<Visitor> getVisitorByName(String name) {
        return visitorRepository.findAll();
    }

    public void deleteAllRoles() {
        visitorRepository.deleteAll();
    }
}


