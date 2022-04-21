package com.example.home2.service;

import com.example.home2.dto.OrderDto;
import com.example.home2.exception.InvalidUUIDException;
import com.example.home2.exception.OrderNotFoundException;
import com.example.home2.mapper.OrderMapper;
import com.example.home2.repository.OrderRepository;
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
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public List<OrderDto> findAll() {
        return orderMapper.modelToDto(orderRepository.findAll());
    }

    public OrderDto findById(UUID id) throws OrderNotFoundException {
        return orderMapper.modelToDto(orderRepository.findById(id).orElseThrow(OrderNotFoundException::new));
    }

    public OrderDto saveNew(@Valid OrderDto orderDto) {
        this.checkIfIdIsEmpty(orderDto);
        return orderMapper.modelToDto(orderRepository.save(orderMapper.dtoToModel(orderDto)));
    }

    public void deleteById(UUID id) throws OrderNotFoundException {
        this.checkIfExists(id);
        orderRepository.deleteById(id);
    }

    private void checkIfExists(UUID id) throws OrderNotFoundException {
        this.findById(id);
    }

    private void checkIfIdIsEmpty(OrderDto orderDto) {
        if (orderDto.getId() != null)
            throw new InvalidUUIDException();
    }

    public OrderDto saveExisting(@Valid OrderDto orderDto) throws OrderNotFoundException {
        this.checkIfExists(orderDto.getId());
        return orderMapper.modelToDto(orderRepository.save(orderMapper.dtoToModel(orderDto)));
    }


}