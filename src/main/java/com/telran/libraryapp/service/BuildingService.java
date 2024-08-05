package com.telran.libraryapp.service;

import com.telran.libraryapp.dto.BuildingDto;
import com.telran.libraryapp.entity.Building;
import com.telran.libraryapp.mapper.BuildingMapper;
import com.telran.libraryapp.repository.BuildingRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Service
public class BuildingService {

    private static final String ZIPCODE_REGEX = "\\s[A-Z]{2}\\d\\s\\d[A-Z]{2}";

    private final BuildingRepository repository;

    private final BuildingMapper buildingMapper;

    @Autowired
    public BuildingService(BuildingRepository repository, BuildingMapper buildingMapper) {
        this.repository = repository;
        this.buildingMapper = buildingMapper;
    }


    public List<BuildingDto> getAll() {
        return buildingMapper.entityListToDto(repository.findAll());
    }


    public Optional<BuildingDto> getBuildingById(Long id) {
        Optional<Building> optional = repository.findById(id);
        Building building = optional.get();
        return Optional.of(buildingMapper.entityToDto(building));
    }


    public BuildingDto addBuilding(BuildingDto buildingDto) {
        Building building = buildingMapper.dtoToEntity(buildingDto);
        Building saved = repository.save(building);
        return buildingMapper.entityToDto(saved);
    }


    public BuildingDto updateBuilding(BuildingDto buildingDto) {
        Optional<Building> optional = repository.findById(buildingDto.getId());
        if (optional.isPresent()) {
            Building saved = repository.save(buildingMapper.dtoToEntity(buildingDto));
            return buildingMapper.entityToDto(saved);
        } else {
            return null;
        }
    }


    public void deleteById(Long id) {
        repository.deleteById(id);
    }


    public List<BuildingDto> getBuildingByName(String name) {
        return buildingMapper.entityListToDto(repository.findBuildingByNameStartingWith(name));
    }


    public void deleteAllZipCodes() { // TODO
//        repository.findAll().forEach(building -> building.setAddress(building.getAddress()
//                .replaceAll(ZIPCODE_REGEX, "")));
    }

}