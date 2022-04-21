package com.example.home2.mapper;

import com.example.home2.dto.ContactDto;
import com.example.home2.model.Contact;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ContactMapper {

    ContactDto modelToDto(Contact contact);

    List<ContactDto> modelToDto(List<Contact> contacts);

    Contact dtoToModel(ContactDto contactDto);
}
