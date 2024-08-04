package com.telran.libraryapp.service;

import com.telran.libraryapp.entity.Building;
import com.telran.libraryapp.repository.BuildingRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
public class BuildingServiceTest {

    private static BuildingService buildingService;

    private static BuildingRepository repository;

    @BeforeAll
    public static void init() {
        repository = Mockito.mock(BuildingRepository.class);
        buildingService = new BuildingService(repository);
    }

    @Test
    public void getAll() {
        buildingService.getAll();
        Mockito.verify(repository).findAll();

    }

    @Test
    public void getBuildingById() {
        Building building = new Building();
        building.setId(3L);

        Mockito.when(repository.findById(3L)).thenReturn(Optional.of(building));

       Building result = buildingService.getBuildingById(3L).get();

        Mockito.verify(repository).findById(3L);
        assertEquals(building.getId(), result.getId());
    }

    @Test
    void addBuilding() {
        Building building = new Building();
        building.setName("Name");

        buildingService.addBuilding(building);

        Mockito.verify(repository).save(building);
    }

    @Test
    void updateBuilding() {
        Building oldBuilding = new Building();
        oldBuilding.setName("Old name");
        oldBuilding.setAddress("Old address");
        oldBuilding.setId(1L);

        Building newBuilding = new Building();
        newBuilding.setName("New name");
        newBuilding.setAddress("New address");
        newBuilding.setId(1L);

        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(oldBuilding));
        Mockito.when(repository.save(newBuilding)).thenReturn(newBuilding);

        Building result = buildingService.updateBuilding(newBuilding);
        Mockito.verify(repository).findById(1L);
        Mockito.verify(repository).save(newBuilding);
        assertEquals(newBuilding.getName(), result.getName());
        assertEquals(newBuilding.getAddress(), result.getAddress());

        newBuilding = new Building();
        newBuilding.setName("New name");
        newBuilding.setAddress("New address");
        newBuilding.setId(555L);

        Mockito.when(repository.findById(555L)).thenReturn(Optional.empty());
        result = buildingService.updateBuilding(newBuilding);
        assertNull(result);


    }

    @Test
    void deleteById() {
        buildingService.deleteById(2L);
        Mockito.verify(repository).deleteById(2L);
    }

    @Test
    void getBuildingByName() {
        String name = "Name";
        buildingService.getBuildingByName(name);
        Mockito.verify(repository).findBuildingByNameStartingWith(name);
    }

    @Test
    void deleteAllZipCodes() {
    }
}