package com.app.interfaces.dto.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class CreateOrderRequestDto {
    @NotBlank
    @Pattern(regexp = "^[0-9]+$", message = "\"company_id\" field must be a number")
    @JsonProperty("company_id")
    private Long company_id;
    
    @NotBlank
    @Pattern(regexp = "^[0-9]+$", message = "\"product_id\" field must be a number")
    @JsonProperty("product_id")
    private Long product_id;

    @NotBlank
    @Pattern(regexp = "^[0-9]+$", message = "\"quantity\" field must be a number")
    @JsonProperty("quantity")
    private Integer quantity;    
}
