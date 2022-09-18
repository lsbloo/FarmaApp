package com.farmawebservice.delivery.products.controller;

import com.farmawebservice.delivery.products.dto.ProductDTO;
import com.farmawebservice.delivery.products.service.ProductsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/products")
@Api(value = "Products API")
public class ProductsController {

    @Autowired
    private ProductsService productsService;


    @PostMapping("/fktory")
    @ApiOperation(value = "generate all products and drugs to catalog app", notes = "")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully created"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500,message = "Error Internal")
    })
    private ResponseEntity<?> setupFktoryProducts(@RequestBody ProductDTO productDTO){
        return this.productsService.insertFktoryProduct(productDTO);
    }

    @GetMapping("/all")
    private ResponseEntity<?> getAllProducts(){
        return ResponseEntity.ok().body(this.productsService.getAllProducts());
    }
}
