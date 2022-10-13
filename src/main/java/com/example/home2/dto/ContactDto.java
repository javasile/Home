package com.example.home2.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Slf4j
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContactDto {

    @Schema(description = "id contact", example = "74478911-6424-47a7-911c-0daa262144ya")
    private UUID id;

    @Schema(description = "contact email, max length 25", example = "alice@example.com")
    @Length(max = 25)
    @Email
    private String email;

    @Schema(description = "contact phone, max length 12", example = "+40712312313")
    @Length(max = 12)
    private String phone;

    @Schema(description = "contact onlineContact, max length 256", example = "Facebook.com")
    @Length(max = 256)
    private String onlineContact;
}
