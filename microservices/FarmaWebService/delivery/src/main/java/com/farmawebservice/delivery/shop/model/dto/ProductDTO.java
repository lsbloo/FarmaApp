package com.farmawebservice.delivery.shop.model.dto;

import com.farmawebservice.delivery.products.model.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@NoArgsConstructor
@Getter
@Setter
public class ProductDTO {

    private Product product;
    private Double value;
    private Double maxValue;
    private Double minValue;
    private Integer amount;
}
