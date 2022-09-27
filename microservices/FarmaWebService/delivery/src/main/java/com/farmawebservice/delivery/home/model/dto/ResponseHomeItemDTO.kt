package com.farmawebservice.delivery.home.model.dto

import com.farmawebservice.delivery.categories.model.dto.CategoryDTO
import com.farmawebservice.delivery.shop.model.ShopProductHighLight

data class ResponseHomeItemDTO(
        var categories: List<CategoryDTO>? = null,
        var highLights: List<ShopProductHighLight>? = null,
        var productsHighLight: List<ShopProductHighLight>? = null
) {
}
