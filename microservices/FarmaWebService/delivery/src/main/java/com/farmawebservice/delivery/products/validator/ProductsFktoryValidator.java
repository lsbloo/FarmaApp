package com.farmawebservice.delivery.products.validator;

import com.farmawebservice.delivery.products.dto.ProductDTO;
import com.farmawebservice.delivery.products.model.Drug;
import com.farmawebservice.delivery.products.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductsFktoryValidator {


    public Object validateFktoryProduct(ProductDTO productDTO) {
        if(productDTO.getIs_drug()) {
            Drug drug = new Drug();
            drug.setApresentation(productDTO.getApresentation());
            drug.setActivePrinciple(productDTO.getActive_principle());
            drug.setBarCode(productDTO.getBarCode());
            drug.setClasse(productDTO.getAtc());
            drug.setCanSell(productDTO.getCan_sell());
            drug.setIsAntibiotic(productDTO.getIs_antibiotic());
            drug.setEan1(productDTO.getEan1());
            drug.setEan2(productDTO.getEan2());
            drug.setManufacturer(productDTO.getBrand());
            drug.setCode(productDTO.getFederal_code());
            drug.setPmc_20(productDTO.getPmc_20());
            return drug;
        } else {
            Product product = new Product();
            product.setDescription(productDTO.getDescription());
            product.setName(productDTO.getName());
            return product;
        }
    }
}
