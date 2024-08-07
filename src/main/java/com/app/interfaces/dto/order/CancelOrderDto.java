package com.app.interfaces.dto.order;

import com.app.domain.database.enums.OrderStatusEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
public class CancelOrderDto {
    @JsonProperty("orderId")
    private Long id;
    
    @JsonProperty("companyId")
    private Long companyid;
    
    @JsonProperty("status")
    private OrderStatusEnum status;
}
