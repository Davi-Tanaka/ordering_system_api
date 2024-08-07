package com.app.interfaces.controller.product;

import com.app.interfaces.dto.product.CreateProduct;
import com.app.interfaces.dto.product.UpdateProduct;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController implements IProductController {

    @PostMapping("/create")
    @Override
    public ResponseEntity create(CreateProduct dto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @PostMapping("/update")
    @Override
    public ResponseEntity update(UpdateProduct dto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @DeleteMapping("/delete")
    @Override
    public ResponseEntity delete(Long product_id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
