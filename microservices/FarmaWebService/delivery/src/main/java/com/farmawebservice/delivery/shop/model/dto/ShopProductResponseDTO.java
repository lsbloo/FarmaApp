package com.farmawebservice.delivery.shop.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class ShopProductResponseDTO implements Serializable {

    private String cnpj;
    private String name;

    private List<ProductDTO> productList;
}
