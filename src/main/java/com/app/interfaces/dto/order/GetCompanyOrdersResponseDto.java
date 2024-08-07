package com.app.interfaces.dto.order;

import com.app.domain.database.entity.OrderEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;
import lombok.NonNull;

@Data
public class GetCompanyOrdersResponseDto {
    @NonNull
    @JsonProperty("company_id")
    private Long company_id;
    
    @NonNull
    @JsonProperty("orders")
    private List<OrderEntity> orders;
}
