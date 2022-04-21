package com.example.home2.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Slf4j
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SellerDto implements Serializable {

    @Schema(description = "id contact", example = "24478911-6424-47a7-911c-0daa262144ya")
    private UUID id;

    @Length(max = 20)
    @NotNull
    @NotBlank
    @Schema(description = "seller firstName, max length 20", example = "Cena", required = true)
    private String firstName;

    @Length(max = 20)
    @NotNull
    @NotBlank
    @Schema(description = "seller lastName, max length 20", example = "John", required = true)
    private String lastName;

    @Schema(description = "list of contact persons")
    private List<ContactDto> contactList;
}
