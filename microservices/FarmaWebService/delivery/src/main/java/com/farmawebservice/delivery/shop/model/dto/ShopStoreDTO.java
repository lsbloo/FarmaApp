package com.farmawebservice.delivery.shop.model.dto;

import com.farmawebservice.delivery.products.model.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class ShopStoreDTO {
    private String client_id_token;
    private String cnpj;
    private String name;
    private List<Product> productList;
}
