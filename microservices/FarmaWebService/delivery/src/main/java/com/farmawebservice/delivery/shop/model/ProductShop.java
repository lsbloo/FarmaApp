package com.farmawebservice.delivery.shop.model;

import com.farmawebservice.delivery.products.model.Product;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "product_shop")
public class ProductShop {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Double value;
    private Double maxValue;
    private Double minValue;
    private Integer amount;
    private String image;

    @OneToOne(fetch = FetchType.EAGER)
    private Product product;
}
