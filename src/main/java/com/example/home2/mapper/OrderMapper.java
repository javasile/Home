package com.example.home2.mapper;

import com.example.home2.dto.OrderDto;
import com.example.home2.model.Order;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderDto modelToDto(Order order);

    List<OrderDto> modelToDto(List<Order> orders);

    Order dtoToModel(OrderDto orderDto);

}
