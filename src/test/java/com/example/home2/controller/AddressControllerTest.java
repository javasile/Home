package com.example.home2.controller;

import com.example.home2.dto.house.AddressDto;
import com.example.home2.model.house.Address;
import com.example.home2.repository.AddressRepository;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AddressControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AddressRepository addressRepository;
    private Address address;

    @BeforeEach
    public void cleanupDatabase() {
        address = Address.builder()
                .country("Romania")
                .county("Ilfov")
                .city("Bucuresti")
                .street("Barbu Vacarescu")
                .number("322A")
                .postalCode("200500")
                .additionalInformation("bl. 10 sc A Ap 434")
                .sellerList(new ArrayList<>())
                .build();
        addressRepository.deleteAll();
        address = addressRepository.save(address);
    }

    @Test
    void testCRUD() throws Exception {
        AddressDto addressDto = AddressDto.builder()
                .id(address.getId())
                .country("Romania")
                .county("Ilfov")
                .city("Bucuresti")
                .street("Barbu Vacarescu")
                .number("322A")
                .postalCode("200500")
                .additionalInformation("bl. 10 sc A Ap 434")
                .sellerList(new ArrayList<>())
                .build();
        mvc.perform(get("/address")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(List.of(addressDto))));
        mvc.perform(put("/address")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(addressDto)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(addressDto)));
        mvc.perform(get("/address/{id}", addressDto.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(addressDto)));
        mvc.perform(delete("/address/{id}", addressDto.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        addressDto.setId(null);
        mvc.perform(post("/address")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(addressDto)))
                .andExpect(status().isOk());
    }


}
