package com.farmawebservice.delivery.products.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ProductDTO {
    @Override
    public String toString() {
        return "ProductDTO{" +
                "active_principle='" + active_principle + '\'' +
                ", code='" + code + '\'' +
                ", barCode='" + barCode + '\'' +
                ", quantity=" + quantity +
                ", manufacturer='" + manufacturer + '\'' +
                ", ean1='" + ean1 + '\'' +
                ", ean2='" + ean2 + '\'' +
                ", restriction='" + restriction + '\'' +
                ", display='" + display + '\'' +
                ", apresentation='" + apresentation + '\'' +
                ", atc='" + atc + '\'' +
                ", tarja='" + tarja + '\'' +
                ", can_sell=" + can_sell +
                ", isAntibiotic=" + is_antibiotic +
                ", category='" + category + '\'' +
                ", name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", description='" + description + '\'' +
                ", is_drug=" + is_drug +
                ", federal_code='" + federal_code + '\'' +
                ", status_type='" + status_type + '\'' +
                ", band_color='" + band_color + '\'' +
                ", pmc_20='" + pmc_20 + '\'' +
                '}';
    }

    private String active_principle;
    private String code;
    private String barCode;
    private Integer quantity;
    private String manufacturer;
    private Long ean1;
    private Long ean2;
    private String restriction;
    private String display;
    private String apresentation;
    private String atc; // classe
    private String tarja;
    private Boolean can_sell;
    private Boolean is_antibiotic;
    private String category;
    private String name;
    private String brand;
    private String description;
    private Boolean is_drug;
    private String federal_code;
    private String status_type;
    private String band_color;
    private String pmc_20;
}
