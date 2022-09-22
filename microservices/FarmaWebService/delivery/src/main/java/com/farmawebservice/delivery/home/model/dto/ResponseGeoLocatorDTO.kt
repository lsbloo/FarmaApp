package com.farmawebservice.delivery.home.model.dto

data class ResponseGeoLocatorDTO(
        val shopStore: ShopStore,
        val deliveryZone: DeliveryZone
){
}

data class DeliveryZone (
        val latitude: Double,
        val longitude: Double,
        val valuePrice: Long,
        val radius: Long
)

data class ShopStore (
        val idShopStore: Long,
        val zones: Any? = null
)