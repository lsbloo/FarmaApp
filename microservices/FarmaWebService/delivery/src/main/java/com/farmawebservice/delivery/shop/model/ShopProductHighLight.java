package com.farmawebservice.delivery.shop.model;


import com.farmawebservice.delivery.products.model.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "shop_product_highlight")
@NoArgsConstructor
@Getter
@Setter
public class ShopProductHighLight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double value;
    private Double maxValue;
    private Double minValue;
    private Integer amount;

    @OneToOne(fetch = FetchType.EAGER)
    private Product product;
}
