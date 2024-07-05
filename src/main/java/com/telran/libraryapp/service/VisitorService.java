package com.telran.libraryapp.service;

import com.telran.libraryapp.entity.Visitor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VisitorService {

    public List<Visitor> getAll() {
        return Visitor.visitorList;
    }

    public Optional<Visitor> getVisitorById(Integer id) {
        return Visitor.visitorList.stream().filter(v -> v.getId().equals(id)).findAny();
    }

    public void addVisitor(Visitor visitor) {
        Visitor.visitorList.add(visitor);
    }

    public boolean updateVisitor(Visitor visitor) {
        int index = Visitor.visitorList.indexOf(visitor);
        if (index != -1) {
            Visitor.visitorList.set(index, visitor);
            return true;
        } else {
            Visitor.visitorList.add(visitor);
            return false;
        }
    }

    public void deleteById(Long id) {
        Visitor.visitorList.removeIf(visitor -> visitor.getId().equals(id));
    }

    public List<Visitor> getVisitorByName(String name) {
        return Visitor.visitorList.stream().filter(v -> v.getName().startsWith(name)).toList();
    }

    public void deleteAllRoles() {
        Visitor.visitorList.forEach(visitor -> visitor.setRole(""));
    }
}


