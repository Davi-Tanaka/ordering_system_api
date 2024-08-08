package com.app.domain.database.repository;

import com.app.domain.database.entity.OrderEntity;
import com.app.domain.database.queryResponse.order.FindOrderByCompanyQueryResponse;
import com.app.domain.database.queryResponse.order.FindOrderByUserQueryResponse;
import com.app.interfaces.dto.order.CancelOrderDto;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OderRepository extends JpaRepository<OrderEntity, Long> {
    @Query(
        value = "SELECT id, product_id, quantity, status. created_at, updated_at FROM " +
                "order_entity WHERE user_id WHERE user_id = ?1",
        nativeQuery = true
    )
    public Optional<FindOrderByUserQueryResponse> findByUser(Long userId);

    @Query(
        value = "SELECT id, user_id, product_id, quantity, status. created_at, updated_at FROM order_entity " +
                "WHERE company_id WHERE company_id = ?1",
        nativeQuery = true
    )
    public Optional<FindOrderByCompanyQueryResponse> findByCompany(Long companyId);
    
    @Query(
        value = "UPDATE status FROM order_entity " + 
                "WHERE id = ?1 RETURNING id, company_id, status;",
        nativeQuery = true
    )
    public Optional<CancelOrderDto> cancelOrder(Long id);
}