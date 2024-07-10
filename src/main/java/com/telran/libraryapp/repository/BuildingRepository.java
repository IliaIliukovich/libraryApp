package com.telran.libraryapp.repository;

import com.telran.libraryapp.entity.Building;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuildingRepository extends JpaRepository<Building, Integer> {

List<Building> findBuildingByNameStartingWith(String name);

}
