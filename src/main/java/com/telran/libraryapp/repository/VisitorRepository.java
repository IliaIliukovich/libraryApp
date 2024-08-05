package com.telran.libraryapp.repository;


import com.telran.libraryapp.entity.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;


@Repository
public interface VisitorRepository extends JpaRepository<Visitor, String> {


    @Query("SELECT v FROM Visitor v WHERE v.name LIKE %?1%")
    List<Visitor> findVisitorsByName(String name);


}

