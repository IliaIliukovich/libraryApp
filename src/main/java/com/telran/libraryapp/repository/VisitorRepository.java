package com.telran.libraryapp.repository;

import com.telran.libraryapp.entity.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface VisitorRepository extends JpaRepository<Visitor, Long> {

}









