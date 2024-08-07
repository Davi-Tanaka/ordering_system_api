package com.app.interfaces.dto.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import lombok.Getter;

@Getter
public class CreateProduct {
    @JsonProperty("name")
    private String name;
    
    @JsonProperty("description")
    private String description;
    
    @JsonProperty("price")
    private Float price;
    
    @JsonProperty("company_id")
    private BigDecimal company_id;
}