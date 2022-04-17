package com.example.home2.dto.client;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Slf4j
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SellerDto {

    @Schema(description = "id contact", example = "24478911-6424-47a7-911c-0daa262144ya")
    private UUID id;

//    @Schema(description = "field, true if is active,", example = "1")
//    private boolean isCompany;

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
