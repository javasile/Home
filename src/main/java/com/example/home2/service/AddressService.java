package com.example.home2.service;

import com.example.home2.dto.AddressDto;
import com.example.home2.exception.AddressNotFoundException;
import com.example.home2.exception.InvalidUUIDException;
import com.example.home2.mapper.AddressMapper;
import com.example.home2.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Service
@Validated
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

    public List<AddressDto> findAll() {
        return addressMapper.modelToDto(addressRepository.findAll());
    }

    public AddressDto findById(UUID id) throws AddressNotFoundException {
        return addressMapper.modelToDto(addressRepository.findById(id).orElseThrow(AddressNotFoundException::new));
    }

    public AddressDto saveNew(@Valid AddressDto addressDto) {
        this.checkIfIdIsEmpty(addressDto);
        return addressMapper.modelToDto(addressRepository.save(addressMapper.dtoToModel(addressDto)));
    }

    public void deleteById(UUID id) throws AddressNotFoundException {
        this.checkIfExists(id);
        addressRepository.deleteById(id);
    }

    private void checkIfExists(UUID id) throws AddressNotFoundException {
        this.findById(id);
    }

    private void checkIfIdIsEmpty(AddressDto addressDto) {
        if (addressDto.getId() == null)
            throw new InvalidUUIDException();
    }

    public AddressDto saveExisting(@Valid AddressDto addressDto) throws AddressNotFoundException {
        this.checkIfExists(addressDto.getId());
        return addressMapper.modelToDto(addressRepository.save(addressMapper.dtoToModel(addressDto)));
    }
}
