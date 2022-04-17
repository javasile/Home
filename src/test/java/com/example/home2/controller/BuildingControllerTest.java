package com.example.home2.controller;

import com.example.home2.dto.client.CustomerDto;
import com.example.home2.dto.house.BuildingDto;
import com.example.home2.dto.house.CategoryDto;
import com.example.home2.model.client.Customer;
import com.example.home2.model.house.Building;
import com.example.home2.model.house.Category;
import com.example.home2.repository.BuildingRepository;
import com.example.home2.repository.CustomerRepository;
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
public class BuildingControllerTest {

        @Autowired
        private MockMvc mvc;

        @Autowired
        private ObjectMapper objectMapper;

        @Autowired
        private BuildingRepository buildingRepository;
        private Building building;

        @BeforeEach
        public void cleanupDatabase() {
            building = Building.builder()
                    .sku("IMOBIL32223")
                    .utility("water, gas, electricity")
                    .description("close to shops, schools")
                    .constructionYear("1990")
                    .price(100556.56)
                    .category(Category.VILLA)
                    .addressList(new ArrayList<>())
                    .build();
            buildingRepository.deleteAll();
            building = buildingRepository.save(building);
        }
        @Test
        void testCRUD() throws Exception {
            BuildingDto buildingDto = BuildingDto.builder()
                    .sku("IMOBIL32223")
                    .utility("water, gas, electricity")
                    .description("close to shops, schools")
                    .constructionYear("1990")
                    .price(100556.56)
                    .category(CategoryDto.VILLA)
                    .addresses(new ArrayList<>())
                    .build();
            mvc.perform(get("/building")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                    .andExpect(content().json(objectMapper.writeValueAsString(List.of(buildingDto))));
            mvc.perform(put("/building")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(buildingDto)))
                    .andExpect(status().isOk())
                    .andExpect(content().json(objectMapper.writeValueAsString(buildingDto)));
            mvc.perform(get("/building/{id}", buildingDto.getId())
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(content().json(objectMapper.writeValueAsString(buildingDto)));
            mvc.perform(delete("/building/{id}", buildingDto.getId())
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
            buildingDto.setId(null);
            mvc.perform(post("/building")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(buildingDto)))
                    .andExpect(status().isOk());
        }
}
