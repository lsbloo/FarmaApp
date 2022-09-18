package com.farmawebservice.delivery.shop.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class ShopStoreProductDTO {
    private String client_id_token;
    private String products_shop_id;
    private String products_shop_highlight_id;
    private List<ShopProductDTO> productList;
    private List<ShopProductHighLightDTO> productHighLightDTOList;
}
