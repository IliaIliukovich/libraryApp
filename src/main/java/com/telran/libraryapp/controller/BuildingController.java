package com.telran.libraryapp.controller;

import com.telran.libraryapp.entity.Building;
import com.telran.libraryapp.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/buildings")
public class BuildingController {

    private final BuildingService service;

    @Autowired
    public BuildingController(BuildingService service) {
        this.service = service;
    }
    @GetMapping
    public List<Building> getAll() {
        return service.getAll();
    }
    @GetMapping("/searchById")
    public ResponseEntity<Building> getBildById(@RequestParam Long id) {
        Optional<Building> building = service.getBuildingById(id);
        if (building.isPresent()) {
            return new ResponseEntity<>(building.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping
    public ResponseEntity<Building> addBuilding(@RequestBody Building building) {
        service.addBuilding(building);
        return new ResponseEntity<>(building, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Building> updateBuilding(@RequestBody Building building) {
        Building updatedBuilding = service.updateBuilding(building);
        return new ResponseEntity<>(building, updatedBuilding != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }


    @DeleteMapping
    public ResponseEntity<?> deleteById(@RequestParam Long id) {
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }


    @GetMapping("/{name}")
    public ResponseEntity<List<Building>> getBuildingByName(@PathVariable String name) {
        List<Building> buildings = service.getBuildingByName(name);
        if (!buildings.isEmpty()) {
            return new ResponseEntity<>(buildings, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PatchMapping("/deleteAllZipCodes")
    public ResponseEntity<?> deleteAllZipCodes() {
        service.deleteAllZipCodes();
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}