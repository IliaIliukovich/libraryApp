package com.telran.libraryapp.controller;

import com.telran.libraryapp.entity.Building;
import com.telran.libraryapp.service.BuildingService;
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

    public BuildingController(BuildingService service) {
        this.service = service;
    }


    @GetMapping
    public List<Building> getAll() {
        return service.getAll();
    }


    @GetMapping("/searchById")
    public ResponseEntity<Building> getBildById(@RequestParam Integer id) {
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
        boolean isUpdated = service.updateBuilding(building);
        return new ResponseEntity<>(building, isUpdated ? HttpStatus.OK : HttpStatus.CREATED);
    }


    @DeleteMapping
    public ResponseEntity<?> deleteById(@RequestParam Integer id) {
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }


    @GetMapping("/searchByName")
    public ResponseEntity<List<Building>> getBuildingByName(@RequestParam String name) {
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
