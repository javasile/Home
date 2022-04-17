package com.example.home2.service;

import com.example.home2.dto.house.AddressDto;
import com.example.home2.dto.house.BuildingDto;
import com.example.home2.exception.AddressNotFoundException;
import com.example.home2.exception.BuildingNotFoundException;
import com.example.home2.exception.InvalidUUIDException;
import com.example.home2.mapper.AddressMapper;
import com.example.home2.mapper.BuildingMapper;
import com.example.home2.repository.AddressRepository;
import com.example.home2.repository.BuildingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Service
@Validated
@RequiredArgsConstructor
public class BuildingService {


    private final BuildingRepository buildingRepository;
    private final BuildingMapper buildingMapper;

    public List<BuildingDto> findAll() {
        return buildingMapper.modelToDto(buildingRepository.findAll());
    }

    public BuildingDto findById(UUID id) throws BuildingNotFoundException {
        return buildingMapper.modelToDto(buildingRepository.findById(id).orElseThrow(BuildingNotFoundException::new));
    }

    public BuildingDto saveNew(@Valid BuildingDto buildingDto) {
        this.checkIfIdIsEmpty(buildingDto);
        return buildingMapper.modelToDto(buildingRepository.save(buildingMapper.dtoToModel(buildingDto)));
    }

    public void deleteById(UUID id) throws BuildingNotFoundException {
        this.checkIfExists(id);
        buildingRepository.deleteById(id);
    }

    private void checkIfExists(UUID id) throws BuildingNotFoundException {
        this.findById(id);
    }

    private void checkIfIdIsEmpty(BuildingDto buildingDto) {
        if (buildingDto.getId() != null)
            throw new InvalidUUIDException();
    }

    public BuildingDto saveExisting(@Valid BuildingDto buildingDto) throws BuildingNotFoundException {
        this.checkIfExists(buildingDto.getId());
        return buildingMapper.modelToDto(buildingRepository.save(buildingMapper.dtoToModel(buildingDto)));
    }
        }
