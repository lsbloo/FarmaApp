package com.farmawebservice.delivery.shop.model.dto;

import com.farmawebservice.delivery.base.BaseDTO;
import com.farmawebservice.delivery.categories.model.dto.CategoryDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ShopCategoryDTO extends BaseDTO {
    private CategoryDTO category;
    private Long category_id;
}
