package com.example.home2.controller;

import com.example.home2.dto.InvoiceDto;
import com.example.home2.exception.InvoiceNotFoundException;
import com.example.home2.service.InvoiceService;
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
@RequestMapping("invoice")
public class InvoiceController {

    private final InvoiceService invoiceService;

    @Operation(summary = "GET request", description = "Get all contacts")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal error")
    }
    )
    @GetMapping
    public List<InvoiceDto> findAll() {
        return invoiceService.findAll();
    }

    @Operation(summary = "GET request", description = "Get contact by id ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal error")
    }
    )
    @GetMapping("/getById")
    public InvoiceDto findById(@RequestParam("id") UUID id) throws InvoiceNotFoundException {
        return invoiceService.findById(id);
    }

    @Operation(summary = "Post request", description = "Save a new contact ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal error")
    }
    )
    @PostMapping
    public InvoiceDto saveNew(@RequestBody InvoiceDto invoiceDto) {
        return invoiceService.saveNew(invoiceDto);
    }

    @Operation(summary = "Put request", description = "Update contact ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal error")
    }
    )
    @PutMapping
    public InvoiceDto saveExisting(@RequestBody InvoiceDto invoiceDto) throws InvoiceNotFoundException {
        return invoiceService.saveExisting(invoiceDto);
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
    public void deleteById(@RequestParam("id") UUID id) throws InvoiceNotFoundException {
        invoiceService.deleteById(id);
    }
}
