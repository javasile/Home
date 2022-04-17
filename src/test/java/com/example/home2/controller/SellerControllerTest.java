package com.example.home2.controller;

import com.example.home2.dto.client.SellerDto;
import com.example.home2.model.client.Seller;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SellerControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SellerRepository sellerRepository;
    private Seller seller;

    @BeforeEach
    public void cleanupDatabase() {
        seller = Seller.builder()
//                .isCompany(true)
                .firstName("Cena")
                .lastName("John")
                .contactList(new ArrayList<>())
                .build();
        sellerRepository.deleteAll();
        seller = sellerRepository.save(seller);
    }

    @Test
    void testCRUD() throws Exception {
        SellerDto sellerDto = SellerDto.builder()
//                .isCompany(true)
                .firstName("Cena")
                .lastName("John")
                .contactList(new ArrayList<>())
                .build();
        mvc.perform(get("/seller")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(List.of(sellerDto))));
        mvc.perform(put("/seller")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sellerDto)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(sellerDto)));
        mvc.perform(get("/seller/{id}", sellerDto.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(sellerDto)));
        mvc.perform(delete("/seller/{id}", sellerDto.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        sellerDto.setId(null);
        mvc.perform(post("/seller")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sellerDto)))
                .andExpect(status().isOk());
    }

}
