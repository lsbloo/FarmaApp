package com.farmawebservice.delivery.shop.validator;


import com.farmawebservice.delivery.base.validator.core.Validator;
import com.farmawebservice.delivery.shop.model.ProductShop;
import com.farmawebservice.delivery.shop.model.ShopProductHighLight;
import com.farmawebservice.delivery.shop.model.ShopStore;
import com.farmawebservice.delivery.shop.model.dto.ShopProductDTO;
import com.farmawebservice.delivery.shop.model.dto.ShopProductHighLightDTO;
import com.farmawebservice.delivery.shop.model.dto.ShopStoreDTO;
import com.farmawebservice.delivery.shop.model.dto.ShopStoreProductDTO;
import com.farmawebservice.delivery.shop.repository.ProductShopRepository;
import com.farmawebservice.delivery.shop.repository.ShopProductHighLightRepository;
import com.farmawebservice.delivery.shop.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class ShopValidator {

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private ProductShopRepository productShopRepository;

    @Autowired
    private ShopProductHighLightRepository shopProductHighLightRepository;

    public Validator<ShopStoreProductDTO> validateShopStoreHaveShopProducts() {
        return (result, shopStore) -> {
            ShopStore shopStore1 = this.shopRepository.findByClientIdToken(shopStore.getClient_id_token());
            if (shopStore1 != null) {
                result.ok();
                result.setResourceMessage("ShopStore found", false);
            } else {
                result.error("");
                result.setResourceMessage("Not ShopStore Found with client token id", true);
            }
        };
    }

    public Validator<ShopStoreDTO> validateIfExists() {
        return (result, shopStore) -> {
            try {
                ShopStore shopStore1 = this.shopRepository.findByClientIdToken(shopStore.getClient_id_token());
                if (shopStore1 != null) {
                    result.error();
                    result.setResourceMessage("Shop Store has created by user", true);
                } else {
                    result.ok();
                    result.setResourceMessage("Shop Store created", false);
                }
            } catch (NullPointerException e) {
                result.ok();
                result.setResourceMessage("Shop Store created", false);
            }
        };
    }


    public Validator<ShopStoreProductDTO> validateIfExistsShopProductHighLight() {
        return (result, shopStoreProductDTO) -> {
            try {
                ShopStore shopStore1 = this.shopRepository.findByClientIdToken(shopStoreProductDTO.getClient_id_token());
                if (shopStore1 != null) {

                    List<Long> shopProductsId = new ArrayList<Long>();
                    for (ShopProductHighLightDTO shopProductDTO : shopStoreProductDTO.getProductHighLightDTOList()) {
                        shopProductsId.add(shopProductDTO.getId_product());
                    }

                    List<ShopProductHighLight> dList = StreamSupport.stream(this.shopProductHighLightRepository.findAllByProductIdIn(shopProductsId).spliterator(), false).collect(Collectors.toList());
                    if (dList.size() >= 1) {
                        result.error("Has Error");
                        result.setResourceMessage("have only shopProductHighLight with same id", true);
                    } else {
                        result.ok();
                        result.setResourceMessage("Inserted Products Shop ok", false);
                    }
                } else {
                    result.error("Has Error");
                    result.setResourceMessage("dont have ShopStore with client_token", true);
                }
            } catch (NullPointerException e) {
                result.error("Has Error");
                result.setResourceMessage("dont have ShopStore with client_token", true);
            }
        };
    }

    public Validator<ShopStoreProductDTO> validateIfExistsShopProduct() {
        return (result, shopStoreProductDTO) -> {
            try {
                ShopStore shopStore1 = this.shopRepository.findByClientIdToken(shopStoreProductDTO.getClient_id_token());
                if (shopStore1 != null) {

                    List<Long> shopProductsId = new ArrayList<Long>();
                    for (ShopProductDTO shopProductDTO : shopStoreProductDTO.getProductList()) {
                        shopProductsId.add(shopProductDTO.getId_product());
                    }

                    List<ProductShop> dList = StreamSupport.stream(this.productShopRepository.findAllByProductIdIn(shopProductsId).spliterator(), false).collect(Collectors.toList());
                    if (dList.size() >= 1) {
                        result.error("Has Error");
                        result.setResourceMessage("have only shopProduct with same id", true);
                    } else {
                        result.ok();
                        result.setResourceMessage("Inserted Products Shop ok", false);
                    }
                } else {
                    result.error("Has Error");
                    result.setResourceMessage("dont have ShopStore with client_token", true);
                }
            } catch (NullPointerException e) {
                result.error("Has Error");
                result.setResourceMessage("dont have ShopStore with client_token", true);
            }
        };
    }
}
