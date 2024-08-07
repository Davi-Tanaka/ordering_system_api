package com.app.interfaces.controller.order;

import com.app.domain.database.entity.OrderEntity;
import com.app.interfaces.dto.order.CancelOrderDto;
import com.app.interfaces.dto.order.CreateOrderResponseDto;
import com.app.interfaces.dto.order.GetCompanyOrdersResponseDto;
import com.app.interfaces.dto.order.GetUserOrdersResponseDto;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.ResponseEntity;

@Tag(name = "Order API endpoints")
public interface IOrderController {

    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Order placed sucessfuly",
            content = @Content(
                schema = @Schema(implementation = CreateOrderResponseDto.class),
                mediaType = "application/json"
            )
        ),
        @ApiResponse(responseCode = "401", description = "Who request the order do not have authorization."),})
    public ResponseEntity placeOrder(Long product_id, Long company_id);

    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Order canceled sucessfuly",
            content = @Content(
                schema = @Schema(
                    implementation = CancelOrderDto.class
                ),
                mediaType = "application/json"
            )
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Who request the order do not have authorization."
        )
    })
    public ResponseEntity<CancelOrderDto> cancelOrder(Long product_id, Long company_id);

    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Order found sucessfuly",
            content = @Content(
                schema = @Schema(
                    implementation = OrderEntity.class
                ),
                mediaType = "application/json"
            )
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Who request the order do not have authorization."
        )
    })
    public ResponseEntity<List<OrderEntity>> getOrder(Long order_id);

    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Order found sucessfuly",
            content = @Content(
                schema = @Schema(
                    implementation = GetUserOrdersResponseDto.class
                ),
                mediaType = "application/json"
            )
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Who request the order do not have authorization."
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Not found."
        )
    })
    public ResponseEntity<GetUserOrdersResponseDto> getUserOrders(Long user_id);

    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Company orders found sucessfuly",
            content = @Content(
                schema = @Schema(
                    implementation = GetCompanyOrdersResponseDto.class
                )
            )
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Who request the order do not have authorization."
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Not found."
        )
    })
    public ResponseEntity<GetCompanyOrdersResponseDto> getCompanyOrders(Long user_id);
}
