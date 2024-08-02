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

    public Optional<Visitor> getVisitorByEmail(String email) {
        return visitorRepository.findById(email);
    }

    public Visitor addVisitor(Visitor visitor) {
        return visitorRepository.save(visitor);
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

    public void deleteVisitorsByEmail(String email) {
        visitorRepository.deleteById(email);
    }

    public List<Visitor> getVisitorByName(String name) {
        return visitorRepository.findAll();
    }

    public void deleteAllRoles() {
        visitorRepository.deleteAll();
    }
}


