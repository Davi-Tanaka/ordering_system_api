package com.app.interfaces.controller.product;

import com.app.interfaces.dto.product.CreateProduct;
import com.app.interfaces.dto.product.UpdateProduct;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "Product API endpoints")
public interface IProductController {
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Created. Product created."),
        @ApiResponse(responseCode = "400", description = "Bad Request. The client send the wrong body format."),
        @ApiResponse(responseCode = "409", description = "Conflict. Product has some information that cannot be inserted in the datanase becaure already exists.")
    })
    public ResponseEntity create(CreateProduct dto);

    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Sucess. Product updated."),
        @ApiResponse(responseCode = "400", description = "Bad Request. The client send the wrong body format."),
        @ApiResponse(responseCode = "404", description = "Product do not found."),
        @ApiResponse(responseCode = "409", description = "Conflict. Product has some information that cannot be inserted in the datanase becaure already exists.")
    })
    public ResponseEntity update(UpdateProduct dto);
    
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Sucess. Product updated."),
        @ApiResponse(responseCode = "400", description = "Bad Request. The client send the wrong body format."),
        @ApiResponse(responseCode = "404", description = "Product do not found.")
    })
    public ResponseEntity delete(Long product_id);
}