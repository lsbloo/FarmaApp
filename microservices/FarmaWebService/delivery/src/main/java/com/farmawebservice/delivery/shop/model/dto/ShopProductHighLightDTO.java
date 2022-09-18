package com.farmawebservice.delivery.shop.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ShopProductHighLightDTO {
    private Long id_product;
    private Double value;
    private Double maxValue;
    private Double minValue;
    private Integer amount;
}
