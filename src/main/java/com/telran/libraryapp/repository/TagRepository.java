package com.telran.libraryapp.repository;

import com.telran.libraryapp.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

}

