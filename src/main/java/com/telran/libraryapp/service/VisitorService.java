package com.telran.libraryapp.service;

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

    public Optional<Visitor> getVisitorById(Long id) {
        return visitorRepository.findById(id);
    }

    public Visitor addVisitor(Visitor visitor) {
        return visitorRepository.save(visitor);
    }

    public boolean updateVisitor(Visitor visitor) {
        Optional<Visitor> optional = visitorRepository.findById(visitor.getId());
        if (optional.isPresent()) {
            visitorRepository.save(visitor);
            return true;
        } else {
            return false;
        }
    }

    public void deleteVisitorsById(Long id) {
        visitorRepository.deleteById(id);
    }

}
