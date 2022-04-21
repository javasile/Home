package com.example.home2.mapper;

import com.example.home2.dto.AddressDto;
import com.example.home2.model.Address;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    AddressDto modelToDto(Address address);

    List<AddressDto> modelToDto(List<Address> addresses);

    Address dtoToModel(AddressDto addressDto);
}
