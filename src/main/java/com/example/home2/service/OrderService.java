package com.example.home2.service;

import com.example.home2.dto.billing.OrderDto;
import com.example.home2.exception.InvalidUUIDException;
import com.example.home2.exception.OrderNotFoundException;
import com.example.home2.mapper.OrderMapper;
import com.example.home2.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Service
@Validated
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;


    public List<OrderDto> findAll() {
        return orderMapper.modelToDto(orderRepository.findAll());
    }

    public OrderDto findById(UUID id) throws OrderNotFoundException {
        return orderMapper.modelToDto(orderRepository.findById(id).orElseThrow(OrderNotFoundException::new));
    }

    public OrderDto saveNew(@Valid OrderDto contactDto) {
        this.checkIfIdIsEmpty(contactDto);
        return orderMapper.modelToDto(orderRepository.save(orderMapper.dtoToModel(contactDto)));
    }

    public void deleteById(UUID id) throws OrderNotFoundException {
        this.checkIfExists(id);
        orderRepository.deleteById(id);
    }

    private void checkIfExists(UUID id) throws OrderNotFoundException {
        this.findById(id);
    }

    private void checkIfIdIsEmpty(OrderDto contactDto) {
        if (contactDto.getId() != null)
            throw new InvalidUUIDException();
    }

    public OrderDto saveExisting(@Valid OrderDto contactDto) throws OrderNotFoundException {
        this.checkIfExists(contactDto.getId());
        return orderMapper.modelToDto(orderRepository.save(orderMapper.dtoToModel(contactDto)));
    }
}
