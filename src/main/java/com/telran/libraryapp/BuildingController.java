package com.telran.libraryapp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/buildings")
public class BuildingController {

    @GetMapping
    public List<Building> getAll() {
        return Building.buildingList;
    }

    @GetMapping("/searchById")
    public ResponseEntity<Building> getBildById(@RequestParam Integer id) {
        Optional<Building> building = Building.buildingList.stream().filter(b -> b.getId() == id).findAny();
            if (building.isPresent()) {
                return new ResponseEntity<>(building.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
    }

    @PostMapping
    public ResponseEntity<Building> addBuilding(@RequestBody Building building) {
        Building.buildingList.add(building);
        return new ResponseEntity<>(building, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Building> updateBuilding(@RequestBody Building building) {
        if (Building.buildingList.contains(building)) {
            int index = Building.buildingList.indexOf(building);
            Building.buildingList.set(index, building);
            return new ResponseEntity<>(building, HttpStatus.OK);
        } else {
            Building.buildingList.add(building);
            return new ResponseEntity<>(building, HttpStatus.CREATED);
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deleteById(@RequestParam Integer id) {
        Building.buildingList.removeIf(building -> building.getId() == id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping("/serchByName")
    public List<Building> getBuildingByName(@RequestParam String name) {
        return Building.buildingList.stream().filter(b -> b.getName().startsWith(name)).toList();
    }


}
