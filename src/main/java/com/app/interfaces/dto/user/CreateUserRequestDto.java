package com.app.interfaces.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "Create User Request Dto.")
@Builder
public class CreateUserRequestDto {

    @Schema(
            description = "The user name, it must have at least 3 character, and 255 characters maximum."
    )

    @NotBlank
    @Size(min = 3, max = 255)
    @JsonProperty("name")
    private String name;

    @Schema(
            description = " User CPF. Must follow the following format: xxx.xxx.xxx-xx"
    )
    @NotBlank
    @CPF
    @JsonProperty("cpf")
    private String cpf;

    @Schema(
            description = "User email."
    )
    @NotBlank
    @Email
    @JsonProperty("email")
    private String email;

    @Schema(
            description = "User password. Must contain at least 6 character, and 255 maximum."
    )
    @NotBlank
    @Size(min = 6, max = 255)
    @JsonProperty("password")
    private String password;

    @Schema(
            description = "User payment"
    )
    @NotNull
    @JsonProperty("payment")
    private BigDecimal payment;
}