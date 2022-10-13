package com.example.home2.controller;

import com.example.home2.dto.InvoiceDto;
import com.example.home2.model.Category;
import com.example.home2.model.Invoice;
import com.example.home2.repository.InvoiceRepository;
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
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class InvoiceControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private InvoiceRepository invoiceRepository;
    private Invoice invoice;

    @BeforeEach
    public void cleanupDatabase() {
        invoice = Invoice.builder()
                .orderNumber("INV0000321")
                .dateAdded(LocalDate.parse("2022-04-20", DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .category(Category.VILLA)
                .customer(null)
                .building(null)
                .commission(8566.25)
                .build();
        invoiceRepository.deleteAll();
        invoice = invoiceRepository.save(invoice);
    }

    @Test
    void testCRUD() throws Exception {
        InvoiceDto orderDto = InvoiceDto.builder()
                .id(invoice.getId())
                .orderNumber("INV0000321")
                .dateAdded(LocalDate.parse("2022-04-20", DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .category(Category.VILLA)
                .customer(null)
                .building(null)
                .commission(8566.25)
                .build();
        mvc.perform(get("/invoice")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(List.of(orderDto))));
        mvc.perform(put("/invoice")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(orderDto)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(orderDto)));
        mvc.perform(get("/invoice/{id}", orderDto.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(orderDto)));
        mvc.perform(delete("/invoice/{id}", orderDto.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        orderDto.setId(null);
        mvc.perform(post("/invoice")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(orderDto)))
                .andExpect(status().isOk());
    }

}
