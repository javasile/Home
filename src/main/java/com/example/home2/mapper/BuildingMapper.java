package com.example.home2.mapper;

import com.example.home2.dto.BuildingDto;
import com.example.home2.model.Building;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface BuildingMapper {

    BuildingDto modelToDto(Building building);

    List<BuildingDto> modelToDto(List<Building> buildings);

    Building dtoToModel(BuildingDto buildingDto);
}
