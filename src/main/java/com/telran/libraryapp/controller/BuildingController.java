package com.telran.libraryapp.controller;

import com.telran.libraryapp.dto.BuildingDto;
import com.telran.libraryapp.service.BuildingService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/buildings")
@Slf4j
public class BuildingController {

    private final BuildingService service;

    @Autowired
    public BuildingController(BuildingService service) {
        this.service = service;
    }

    @GetMapping
    public List<BuildingDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/searchById")
    public ResponseEntity<BuildingDto> getBuildingById(@RequestParam Long id) {
        Optional<BuildingDto> building = service.getBuildingById(id);
        if (building.isPresent()) {
            return new ResponseEntity<>(building.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping
    public ResponseEntity<BuildingDto> addBuilding(@RequestBody @Valid BuildingDto building) {
        BuildingDto added = service.addBuilding(building);
        log.info("Building with id = {} and name = {} created", added.getId(), added.getName());
        return new ResponseEntity<>(added, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<BuildingDto> updateBuilding(@RequestBody @Valid BuildingDto building) {
        BuildingDto updatedBuilding = service.updateBuilding(building);
        log.info(updatedBuilding != null ? "Building with id = " + building.getId() + " updated" : "Building is not updated. Id = " + building.getId() + " does not exist");
        return new ResponseEntity<>(updatedBuilding, updatedBuilding != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }


    @DeleteMapping
    public ResponseEntity<?> deleteById(@RequestParam Long id) {
        service.deleteById(id);
        log.info("Building with id = {} deleted", id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }


    @GetMapping("/{name}")
    public ResponseEntity<List<BuildingDto>> getBuildingByName(@PathVariable String name) {
        List<BuildingDto> buildings = service.getBuildingByName(name);
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