package com.app.domain.database.queryResponse.order;

import com.app.domain.database.entity.product.ProductEntity;
import com.app.domain.database.entity.user.UserEntity;
import com.app.domain.database.enums.OrderStatusEnum;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class FindOrderByCompanyQueryResponse {
    
    private Long id;
    private UserEntity user;
    private ProductEntity product;
    private Integer quantity;
    private OrderStatusEnum status = OrderStatusEnum.PAID;
    private Timestamp createdAt = Timestamp.valueOf(LocalDateTime.now());
    private Timestamp updatedAt = Timestamp.valueOf(LocalDateTime.now());
}
