package com.telran.libraryapp.repository;


import com.telran.libraryapp.entity.Visitor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class VisitorRepository {
    public List<Visitor> getAll() {
        return Visitor.visitorList;
    }
}

