package com.example.home2.controller;

import com.example.home2.dto.billing.OrderDto;
import com.example.home2.dto.client.CustomerDto;
import com.example.home2.dto.house.BuildingDto;
import com.example.home2.dto.house.CategoryDto;
import com.example.home2.model.billing.Contract;
import com.example.home2.model.billing.Order;
import com.example.home2.model.client.Customer;
import com.example.home2.model.house.Building;
import com.example.home2.model.house.Category;
import com.example.home2.repository.BuildingRepository;
import com.example.home2.repository.OrderRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerTest {

        @Autowired
        private MockMvc mvc;

        @Autowired
        private ObjectMapper objectMapper;

        @Autowired
        private OrderRepository orderRepository;
        private Order order = Order.builder()
                .orderNumber("ORDER: 00000001")
                .dateAdded(LocalDate.parse("2022-04-17"))
                .contract(Contract.HIRE)
                .customer(new Customer())
                .building(new Building())
                .build();

        @BeforeEach
        public void cleanupDatabase() {
            order = Order.builder()
                    .orderNumber("ORDER: 00000001")
                    .dateAdded(LocalDate.parse("2022-04-17"))
                    .contract(Contract.HIRE)
                    .customer(new Customer())
                    .building(new Building())
                    .build();
            orderRepository.deleteAll();
            order = orderRepository.save(order);
        }
        @Test
        void testCRUD() throws Exception {
            OrderDto orderDto = OrderDto.builder()
                    .orderNumber("ORDER: 00000001")
                    .dateAdded(LocalDate.parse("2022-04-17"))
                    .contract(Contract.HIRE)
                    .customer(new CustomerDto())
                    .building(new BuildingDto())
                    .build();
            mvc.perform(get("/order")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                    .andExpect(content().json(objectMapper.writeValueAsString(List.of(orderDto))));
            mvc.perform(put("/order")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(orderDto)))
                    .andExpect(status().isOk())
                    .andExpect(content().json(objectMapper.writeValueAsString(orderDto)));
            mvc.perform(get("/order/{id}", orderDto.getId())
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(content().json(objectMapper.writeValueAsString(orderDto)));
            mvc.perform(delete("/order/{id}", orderDto.getId())
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
            orderDto.setId(null);
            mvc.perform(post("/order")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(orderDto)))
                    .andExpect(status().isOk());
        }
}
