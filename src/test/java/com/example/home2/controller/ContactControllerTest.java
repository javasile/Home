package com.example.home2.controller;

import com.example.home2.dto.ContactDto;
import com.example.home2.model.Contact;
import com.example.home2.repository.ContactRepository;
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

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class ContactControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ContactRepository contactRepository;
    private Contact contact;

    @BeforeEach
    public void cleanupDatabase() {
        contact = Contact.builder()
                .email("asd@asd.com")
                .phone("321564")
                .onlineContact("Facebook.com")
                .build();
        contactRepository.deleteAll();
        contact = contactRepository.save(contact);
    }

    @Test
    void testCRUD() throws Exception {
        ContactDto contactDto = ContactDto.builder()
                .id(contact.getId())
                .email("asd@asd.com")
                .phone("321564")
                .onlineContact("Facebook.com")
                .build();
        mvc.perform(get("/contact")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(List.of(contactDto))));
        mvc.perform(put("/contact")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(contactDto)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(contactDto)));
        mvc.perform(get("/contact/{id}", contactDto.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(contactDto)));
        mvc.perform(delete("/contact/{id}", contactDto.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        contactDto.setId(null);
        mvc.perform(post("/contact")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(contactDto)))
                .andExpect(status().isOk());
    }

}
