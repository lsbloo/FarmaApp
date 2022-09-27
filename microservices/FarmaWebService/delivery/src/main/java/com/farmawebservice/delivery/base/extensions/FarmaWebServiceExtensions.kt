package com.farmawebservice.delivery.base.extensions

import com.farmawebservice.delivery.categories.model.Category
import com.farmawebservice.delivery.categories.model.dto.CategoryDTO
import com.farmawebservice.delivery.deliveryzone.model.DeliveryZone
import com.farmawebservice.delivery.deliveryzone.model.dto.DeliveryZoneDTO
import com.farmawebservice.delivery.shop.model.ShopProductHighLight

object FarmaWebServiceExtensions {
    fun List<DeliveryZone>.convertToDeliveryZoneDTO(): List<DeliveryZoneDTO> {
        val dListDTO = ArrayList<DeliveryZoneDTO>()
        this.forEach { deliveryZone ->
            dListDTO.add(DeliveryZoneDTO().apply {
                id = deliveryZone.id
                latitude = deliveryZone.latitude
                longitude = deliveryZone.longitude
                radius = deliveryZone.radius
                valuePrice = deliveryZone.valuePrice
            })
        }
        return dListDTO
    }

    fun List<Category>.convertToDTO(): List<CategoryDTO> {
        val dListDTO = ArrayList<CategoryDTO>()
        this.forEach {
            val category = CategoryDTO()
            category.name = it.name
            category.type = it.type
            category.urlImage = it.urlImage
            dListDTO.add(category)
        }
        return dListDTO
    }


    fun List<ShopProductHighLight>.getOnlyHighLights(): List<ShopProductHighLight> {
        val dListDTO = ArrayList<ShopProductHighLight>()
        this.forEach {
            shopProductHighLight ->
            if(shopProductHighLight.isHighLightOnly != null && shopProductHighLight.isHighLightOnly == true) {
                dListDTO.add(shopProductHighLight)
            }
        }
        return dListDTO
    }

    fun List<ShopProductHighLight>.getShopProductHighLights(): List<ShopProductHighLight> {
        val dListDTO = ArrayList<ShopProductHighLight>()
        this.forEach {
            shopProductHighLight ->
            if(shopProductHighLight.isHighLightOnly != null && shopProductHighLight.isHighLightOnly == false) {
                dListDTO.add(shopProductHighLight)
            }
        }
        return dListDTO
    }
}