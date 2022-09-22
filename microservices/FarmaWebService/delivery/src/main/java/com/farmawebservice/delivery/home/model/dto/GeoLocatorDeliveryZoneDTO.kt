package com.farmawebservice.delivery.home.model.dto

import com.farmawebservice.delivery.deliveryzone.model.dto.DeliveryZoneDTO

data class GeoLocatorDeliveryZoneDTO(
        var homeItemDTO: HomeItemDTO,
        var shopStore: List<ShopStoreGeoLocatorDTO>,
) {
}

data class ShopStoreGeoLocatorDTO(var idShopStore: Long = 0L,
                                  var deliveryZones: List<DeliveryZoneDTO>) {}