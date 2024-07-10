package com.telran.libraryapp.repository;

import com.telran.libraryapp.entity.Building;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BuildingRepository {

    public List<Building> getAll() {
        return Building.buildingList;
    }

}
