package com.farmawebservice.delivery.products.service;

import com.farmawebservice.delivery.products.dto.ProductDTO;
import com.farmawebservice.delivery.products.model.Drug;
import com.farmawebservice.delivery.products.model.Product;
import com.farmawebservice.delivery.products.repository.DrugsRepository;
import com.farmawebservice.delivery.products.repository.ProductsRepository;
import com.farmawebservice.delivery.products.validator.ProductsFktoryValidator;
import com.farmawebservice.delivery.util.messages.dtos.MessageResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.farmawebservice.delivery.util.messages.dtos.MessageResponseDTO.setupFktoryMessageBadRequest;
import static com.farmawebservice.delivery.util.messages.dtos.MessageResponseDTO.setupFktoryMessageOK;

@Service
public class ProductsService {

    private final ProductsRepository productRepository;
    private final DrugsRepository drugsRepository;
    private final ProductsFktoryValidator productsFktoryValidator;

    @Autowired
    public ProductsService(ProductsRepository productRepository, DrugsRepository drugsRepository,ProductsFktoryValidator productsFktoryValidator) {
        this.productRepository = productRepository;
        this.drugsRepository = drugsRepository;
        this.productsFktoryValidator = productsFktoryValidator;
    }

    public List<Product> getAllProducts(){
        return this.productRepository.getAllProducts();
    }

    public ResponseEntity<MessageResponseDTO> insertFktoryProduct(ProductDTO productDTO) {
        Object objectValidator = productsFktoryValidator.validateFktoryProduct(productDTO);
        if (objectValidator instanceof Product) {
            productRepository.save((Product) objectValidator);
            return ResponseEntity.accepted().body(setupFktoryMessageOK(productDTO));
        } else if (objectValidator instanceof Drug) {
            Product p1 = new Product();
            p1.setName(productDTO.getName());
            p1.setDescription(productDTO.getApresentation());
            drugsRepository.save((Drug) objectValidator);
            p1.setDrug((Drug) objectValidator);
            productRepository.save(p1);
            return ResponseEntity.accepted().body(setupFktoryMessageOK(productDTO));
        } else {
            return ResponseEntity.badRequest().body(setupFktoryMessageBadRequest(productDTO));
        }
    }

}
