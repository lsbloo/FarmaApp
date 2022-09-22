package com.farmawebservice.delivery.base.extensions

import com.farmawebservice.delivery.deliveryzone.model.DeliveryZone
import com.farmawebservice.delivery.deliveryzone.model.dto.DeliveryZoneDTO

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
}