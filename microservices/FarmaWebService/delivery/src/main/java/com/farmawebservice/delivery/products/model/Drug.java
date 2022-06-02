package com.farmawebservice.delivery.products.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity(name = "drugs")
public class Drug {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String activePrinciple;
    private String code;
    private String barCode;
    private Integer quantity;
    private String manufacturer;
    private Long ean1;
    private Long ean2;
    private String restriction;
    private String apresentation;
    private String classe;
    private String tarja;
    private Boolean canSell;
    private Boolean isAntibiotic;
    private String pmc_20;

}
