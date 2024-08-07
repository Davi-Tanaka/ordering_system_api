package com.app.interfaces.dto.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteOrderRequestDto {
    
    @NotBlank
    @Pattern(regexp = "/^\\d+$/")
    @JsonProperty("orderId")
    private Long orderId;
    
    @NotBlank
    @Pattern(regexp = "/^\\d+$/")
    @JsonProperty("companyId")
    private Long companyId;
}
