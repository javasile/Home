package com.example.home2.mapper;

import com.example.home2.dto.client.ContactDto;
import com.example.home2.dto.house.AddressDto;
import com.example.home2.model.client.Contact;
import com.example.home2.model.house.Address;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ContactMapper {

    ContactDto modelToDto(Contact contact);

    List<ContactDto> modelToDto(List<Contact> contacts);

    Contact dtoToModel(ContactDto contactDto);
}
