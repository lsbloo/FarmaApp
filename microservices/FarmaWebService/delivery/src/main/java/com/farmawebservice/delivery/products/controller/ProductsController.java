package com.farmawebservice.delivery.products.controller;

import com.farmawebservice.delivery.products.dto.ProductDTO;
import com.farmawebservice.delivery.products.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/products")
public class ProductsController {

    @Autowired
    private ProductsService productsService;


    @PostMapping("/fktory")
    private ResponseEntity<?> setupFktoryProducts(@RequestBody ProductDTO productDTO){
        return this.productsService.insertFktoryProduct(productDTO);
    }

    @GetMapping("/all")
    private ResponseEntity<?> getAllProducts(){
        return ResponseEntity.ok().body(this.productsService.getAllProducts());
    }
}
