package com.example.home2.service;

import com.example.home2.dto.CustomerDto;
import com.example.home2.exception.CustomerNotFoundException;
import com.example.home2.exception.InvalidUUIDException;
import com.example.home2.mapper.CustomerMapper;
import com.example.home2.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Service
@Validated
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public List<CustomerDto> findAll() {
        return customerMapper.modelToDto(customerRepository.findAll());
    }

    public CustomerDto findById(UUID id) throws CustomerNotFoundException {
        return customerMapper.modelToDto(customerRepository.findById(id).orElseThrow(CustomerNotFoundException::new));
    }

    public CustomerDto saveNew(@Valid CustomerDto customerDto) {
        this.checkIfIdIsEmpty(customerDto);
        return customerMapper.modelToDto(customerRepository.save(customerMapper.dtoToModel(customerDto)));
    }

    public void deleteById(UUID id) throws CustomerNotFoundException {
        this.checkIfExists(id);
        customerRepository.deleteById(id);
    }

    private void checkIfExists(UUID id) throws CustomerNotFoundException {
        this.findById(id);
    }

    private void checkIfIdIsEmpty(CustomerDto customerDto) {
        if (customerDto.getId() == null)
            throw new InvalidUUIDException();
    }

    public CustomerDto saveExisting(@Valid CustomerDto customerDto) throws CustomerNotFoundException {
        this.checkIfExists(customerDto.getId());
        return customerMapper.modelToDto(customerRepository.save(customerMapper.dtoToModel(customerDto)));
    }
}
