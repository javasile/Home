package com.example.home2.controller;

import com.example.home2.dto.ContactDto;
import com.example.home2.exception.ContactNotFoundException;
import com.example.home2.service.ContactService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("contact")
public class ContactController {
    private final ContactService contactService;

    @Operation(summary = "GET request", description = "Get all contacts")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal error")
    }
    )
    @GetMapping
    public List<ContactDto> findAll() {
        return contactService.findAll();
    }

    @Operation(summary = "GET request", description = "Get contact by id ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal error")
    }
    )
    @GetMapping("/getById")
    public ContactDto findById(@RequestParam("id") UUID id) throws ContactNotFoundException {
        return contactService.findById(id);
    }

    @Operation(summary = "Post request", description = "Save a new contact ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal error")
    }
    )
    @PostMapping
    public ContactDto saveNew(@RequestBody ContactDto contactDto) {
        return contactService.saveNew(contactDto);
    }

    @Operation(summary = "Put request", description = "Update contact ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal error")
    }
    )
    @PutMapping
    public ContactDto saveExisting(@RequestBody ContactDto contactDto) throws ContactNotFoundException {
        return contactService.saveExisting(contactDto);
    }

    @Operation(summary = "Delete request", description = "Delete contact by id ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal error")
    }
    )
    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@RequestParam("id") UUID id) throws ContactNotFoundException {
        contactService.deleteById(id);
    }
}

