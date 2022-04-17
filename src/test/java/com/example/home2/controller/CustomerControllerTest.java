package com.example.home2.controller;

import com.example.home2.dto.client.CustomerDto;
import com.example.home2.dto.client.SellerDto;
import com.example.home2.model.client.Customer;
import com.example.home2.model.client.Seller;
import com.example.home2.repository.CustomerRepository;
import com.example.home2.repository.SellerRepository;
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

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerTest {


        @Autowired
        private MockMvc mvc;

        @Autowired
        private ObjectMapper objectMapper;

        @Autowired
        private CustomerRepository customerRepository;
        private Customer customer;

        @BeforeEach
        public void cleanupDatabase() {
            customer = Customer.builder()
                    .firstName("Ionescu")
                    .lastName("Georgescu")
                    .contactList(new ArrayList<>())
                    .build();
            customerRepository.deleteAll();
            customer = customerRepository.save(customer);
        }
    @Test
    void testCRUD() throws Exception {
        CustomerDto customerDto = CustomerDto.builder()
//                .isCompany(true)
                .firstName("Ionescu")
                .lastName("Georgescu")
                .contactList(new ArrayList<>())
                .build();
        mvc.perform(get("/customer")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(List.of(customerDto))));
        mvc.perform(put("/customer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerDto)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(customerDto)));
        mvc.perform(get("/customer/{id}", customerDto.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(customerDto)));
        mvc.perform(delete("/customer/{id}", customerDto.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        customerDto.setId(null);
        mvc.perform(post("/customer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerDto)))
                .andExpect(status().isOk());
    }
}
