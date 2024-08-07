package com.app.interfaces.dto.order;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CreateOrderResponseDto (
    @JsonProperty("id")
    Long id,

    @JsonProperty("company_id")
    Long company_id,

    @JsonProperty("product_id")
    Long product_id,

    @JsonProperty("quantity")
    Integer quantity) 
{}
