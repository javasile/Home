package com.example.home2.service;

import com.example.home2.dto.InvoiceDto;
import com.example.home2.exception.InvalidUUIDException;
import com.example.home2.exception.InvoiceNotFoundException;
import com.example.home2.mapper.InvoiceMapper;
import com.example.home2.repository.InvoiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Service
@Validated
@RequiredArgsConstructor
@Transactional
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final InvoiceMapper invoiceMapper;

    public List<InvoiceDto> findAll() {
        return invoiceMapper.modelToDto(invoiceRepository.findAll());
    }

    public InvoiceDto findById(UUID id) throws InvoiceNotFoundException {
        return invoiceMapper.modelToDto(invoiceRepository.findById(id).orElseThrow(InvoiceNotFoundException::new));
    }

    public InvoiceDto saveNew(@Valid InvoiceDto invoiceDto) {
        this.checkIfIdIsEmpty(invoiceDto);
        return invoiceMapper.modelToDto(invoiceRepository.save(invoiceMapper.dtoToModel(invoiceDto)));
    }

    public void deleteById(UUID id) throws InvoiceNotFoundException {
        this.checkIfExists(id);
        invoiceRepository.deleteById(id);
    }

    private void checkIfExists(UUID id) throws InvoiceNotFoundException {
        this.findById(id);
    }

    private void checkIfIdIsEmpty(InvoiceDto invoiceDto) {
        if (invoiceDto.getId() == null)
            throw new InvalidUUIDException();
    }

    public InvoiceDto saveExisting(@Valid InvoiceDto invoiceDto) throws InvoiceNotFoundException {
        this.checkIfExists(invoiceDto.getId());
        return invoiceMapper.modelToDto(invoiceRepository.save(invoiceMapper.dtoToModel(invoiceDto)));
    }


}