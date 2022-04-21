package com.example.home2.service;

import com.example.home2.dto.SellerDto;
import com.example.home2.exception.InvalidUUIDException;
import com.example.home2.exception.SellerNotFoundException;
import com.example.home2.mapper.SellerMapper;
import com.example.home2.repository.SellerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Service
@Validated
@RequiredArgsConstructor
public class SellerService {

    private final SellerRepository sellerRepository;
    private final SellerMapper sellerMapper;

    public List<SellerDto> findAll() {
        return sellerMapper.modelToDto(sellerRepository.findAll());
    }

    public SellerDto findById(UUID id) throws SellerNotFoundException {
        return sellerMapper.modelToDto(sellerRepository.findById(id).orElseThrow(SellerNotFoundException::new));
    }

    public SellerDto saveNew(@Valid SellerDto sellerDto) {
        this.checkIfIdIsEmpty(sellerDto);
        return sellerMapper.modelToDto(sellerRepository.save(sellerMapper.dtoToModel(sellerDto)));
    }

    public void deleteById(UUID id) throws SellerNotFoundException {
        this.checkIfExists(id);
        sellerRepository.deleteById(id);
    }

    private void checkIfExists(UUID id) throws SellerNotFoundException {
        this.findById(id);
    }

    private void checkIfIdIsEmpty(SellerDto sellerDto) {
        if (sellerDto.getId() != null)
            throw new InvalidUUIDException();
    }

    public SellerDto saveExisting(@Valid SellerDto sellerDto) throws SellerNotFoundException {
        this.checkIfExists(sellerDto.getId());
        return sellerMapper.modelToDto(sellerRepository.save(sellerMapper.dtoToModel(sellerDto)));
    }
}
