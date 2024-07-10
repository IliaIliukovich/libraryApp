package com.telran.libraryapp.service;

import com.telran.libraryapp.entity.Building;
import com.telran.libraryapp.repository.BuildingRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Service
public class BuildingService {

    private static final String ZIPCODE_REGEX = "\\s[A-Z]{2}\\d\\s\\d[A-Z]{2}";
    private final BuildingRepository repository;

    @Autowired
    public BuildingService(BuildingRepository repository) {
        this.repository = repository;
    }


    public List<Building> getAll() {
        return repository.findAll();
    }


    public Optional<Building> getBuildingById(Integer id) {
        return repository.findById(id);
    }


    public void addBuilding(Building building) {
        repository.save(building);
    }


    public Building updateBuilding(Building building) {
        Optional<Building> optional = repository.findById(building.getId());
        if (optional.isPresent()) {
            Building saved = repository.save(building);
            return saved;
        } else {
            return null;
        }
    }


    public void deleteById(Integer id) {
        repository.deleteById(id);
    }


    public List<Building> getBuildingByName(String name) {
        return repository.findBuildingByNameStartingWith(name);
    }


    public void deleteAllZipCodes() {
        repository.findAll().forEach(building -> building.setAddress(building.getAddress()
                .replaceAll(ZIPCODE_REGEX, "")));
    }

}