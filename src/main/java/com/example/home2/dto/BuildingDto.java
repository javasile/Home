package com.example.home2.dto;

import com.example.home2.model.Category;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import java.util.List;
import java.util.UUID;

@Slf4j
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BuildingDto {

    @Schema(description = "id building", example = "1c263004-6df9-4879-a3d9-9baf22ccdc18")
    private UUID id;

    @Schema(description = "building sku,max length 30", example = "IMOBIL325562")
    @Length(max = 30)
    private String sku;

    @Schema(description = "building utility,max length 255", example = "water, gas, electricity, own boiler")
    @Length(max = 255)
    private String utility;

    @Schema(description = "building description, max 255 characters", example = "Sunny, close to shops, schools, kindergartens")
    @Length(max = 255)
    private String description;

    @Schema(description = "building constructionYear,max length 30", example = "before 1977")
    @Length(max = 30)
    private String constructionYear;

    @Schema(description = "building price, the value must be 0 or positive", example = "0")
    @Min(value = 0L, message = "The value must be positive")
    private  Double price;

    @Schema(description = "building category, the value must be 0 or positive", example = "VILLA, APARTMENT, SINGLE_ROOM")
    private Category category;

    @Schema(description = "list of sellers")
    private List<AddressDto> addresses;
}
