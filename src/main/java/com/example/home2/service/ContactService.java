package com.example.home2.service;

import com.example.home2.dto.ContactDto;
import com.example.home2.exception.ContactNotFoundException;
import com.example.home2.exception.InvalidUUIDException;
import com.example.home2.mapper.ContactMapper;
import com.example.home2.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Service
@Validated
@RequiredArgsConstructor
public class ContactService {
    private final ContactRepository contactRepository;
    private final ContactMapper contactMapper;

    public List<ContactDto> findAll() {
        return contactMapper.modelToDto(contactRepository.findAll());
    }

    public ContactDto findById(UUID id) throws ContactNotFoundException {
        return contactMapper.modelToDto(contactRepository.findById(id).orElseThrow(ContactNotFoundException::new));
    }

    public ContactDto saveNew(@Valid ContactDto contactDto) {
        this.checkIfIdIsEmpty(contactDto);
        return contactMapper.modelToDto(contactRepository.save(contactMapper.dtoToModel(contactDto)));
    }

    public void deleteById(UUID id) throws ContactNotFoundException {
        this.checkIfExists(id);
        contactRepository.deleteById(id);
    }

    private void checkIfExists(UUID id) throws ContactNotFoundException {
        this.findById(id);
    }

    private void checkIfIdIsEmpty(ContactDto contactDto) {
        if (contactDto.getId() == null)
            throw new InvalidUUIDException();
    }

    public ContactDto saveExisting(@Valid ContactDto contactDto) throws ContactNotFoundException {
        this.checkIfExists(contactDto.getId());
        return contactMapper.modelToDto(contactRepository.save(contactMapper.dtoToModel(contactDto)));
    }
}
