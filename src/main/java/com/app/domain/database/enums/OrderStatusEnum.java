package com.app.domain.database.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderStatusEnum {
    PAID("DONE"),
    REFUNDED("REFUNDED"),
    CANCELED("CANCELED");
    
    private String status;
}
