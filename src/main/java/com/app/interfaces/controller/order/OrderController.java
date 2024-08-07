package com.app.interfaces.controller.order;

import com.app.domain.database.entity.OrderEntity;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController implements IOrderController {

    @PostMapping("/placeOrder")
    @Override
    public ResponseEntity placeOrder(Long product_id, Long company_id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @PostMapping("/cancelOrder")
    @Override
    public ResponseEntity cancelOrder(Long product_id, Long company_id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @PostMapping("/{order_id}")
    @ResponseBody
    @Override
    public ResponseEntity<List<OrderEntity>> getOrder(@RequestParam(name = "order_id") Long order_id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @GetMapping("/of/user/{user_id}")
    @Override
    public ResponseEntity getUserOrders(@RequestParam(name = "user_id") Long user_id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @GetMapping("/of/company/{company_id}")
    @Override
    public ResponseEntity getCompanyOrders(@RequestParam(name = "company_id") Long company_id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
