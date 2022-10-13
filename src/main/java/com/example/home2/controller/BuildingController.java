package com.example.home2.controller;

import com.example.home2.dto.BuildingDto;
import com.example.home2.exception.BuildingNotFoundException;
import com.example.home2.service.BuildingService;
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
@RequestMapping("/building")
public class BuildingController {

    private final BuildingService buildingService;

    @Operation(summary = "GET request", description = "Get all contacts")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal error")
    }
    )
    @GetMapping
    public List<BuildingDto> findAll() {
        return buildingService.findAll();
    }

    @Operation(summary = "GET request", description = "Get contact by id ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal error")
    }
    )
    @GetMapping("/getById")
    public BuildingDto findById(@RequestParam("id") UUID id) throws BuildingNotFoundException {
        return buildingService.findById(id);
    }

    @Operation(summary = "Post request", description = "Save a new contact ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal error")
    }
    )
    @PostMapping
    public BuildingDto saveNew(@RequestBody BuildingDto buildingDto) {
        return buildingService.saveNew(buildingDto);
    }

    @Operation(summary = "Put request", description = "Update contact ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal error")
    }
    )
    @PutMapping
    public BuildingDto saveExisting(@RequestBody BuildingDto buildingDto) throws BuildingNotFoundException {
        return buildingService.saveExisting(buildingDto);
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
    public void deleteById(@RequestParam("id") UUID id) throws BuildingNotFoundException {
        buildingService.deleteById(id);
    }
}
