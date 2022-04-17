package com.example.home2.dto.billing;

import com.example.home2.dto.client.CustomerDto;
import com.example.home2.dto.house.BuildingDto;
import com.example.home2.model.billing.Contract;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Slf4j
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    @Schema(description = "id order", example = "74478911-6424-4b77-911c-0daa262144fa")
    private UUID id;

    @Schema(description = "Invoice number, max length 30", example = "ORDER: 0000321")
    @Length(max = 30)
    private String orderNumber;

    @Schema(description = "date added modified",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateAdded;

    @Enumerated(EnumType.STRING)
    private Contract contract;

    @Schema(description = "Customer object, if is null will not associated a customer")
    private CustomerDto customer;

    @Schema(description = "Building object list, if is null will not associated a building")
    private BuildingDto building;


}
