package com.telran.libraryapp.service;

import com.telran.libraryapp.entity.Building;
import com.telran.libraryapp.repository.BuildingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BuildingService {

    private final BuildingRepository repository;


    public BuildingService(BuildingRepository repository) {
        this.repository = repository;
    }


    public List<Building> getAll() {
        return repository.getAll();
    }


    public Optional<Building> getBuildingById(Integer id) {
        return repository.getAll().stream().filter(b -> b.getId() == id).findAny();
    }


    public void addBuilding(Building building) {
        repository.getAll().add(building);
    }


    public boolean updateBuilding(Building building) {
        List<Building> buildingList = repository.getAll();
        if (buildingList.contains(building)) {
            int index = repository.getAll().indexOf(building);
            repository.getAll().set(index, building);
            return true;
        } else {
            repository.getAll().add(building);
            return false;
        }
    }


    public void deleteById(Integer id) {
        repository.getAll().removeIf(building -> building.getId() == id);
    }


    public List<Building> getBuildingByName(String name) {
        return repository.getAll().stream().filter(b -> b.getName().startsWith(name)).toList();
    }


    public void deleteAllZipCodes() {
        repository.getAll().stream().forEach(building -> building.setAddress(building.getAddress()
                .replaceAll("\\s[A-Z]{2}\\d\\s\\d[A-Z]{2}", "")));
    }

}
