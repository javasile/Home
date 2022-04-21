package com.example.home2.dto;

import com.example.home2.model.Category;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Console;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Slf4j
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto implements Serializable {

    @Schema(description = "id customer", example = "74478911-6424-47a7-911c-0daa262144fa")
    private UUID id;

    @Schema(description = "Invoice number, max length 30", example = "INV0000321")
    @Length(max = 30)
    private String orderNumber;

    @Schema(description = "date added modified", pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateAdded;

    @Schema(description = "category added modified", pattern = "SALE")
    private Category category;

    @Schema(description = "Customer object, if is null will not associated a customer")
    private CustomerDto customer;

    @Schema(description = "building object, if is null will not associated a building")
    private BuildingDto building;

    @Schema(description = "commission, if is null will not associated a building")
    private double commission;



}
