package com.farmawebservice.delivery.products.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private Double value;
    private Double maxValue;
    private Double minValue;
    private String description;

    @OneToOne
    private Drug drug;
}
