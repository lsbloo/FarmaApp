package com.farmawebservice.delivery.deliveryzone.model.dto

import com.farmawebservice.delivery.base.BaseDTO

data class DeliveryZoneDTO(
        var latitude: Double = 0.0,
        var longitude: Double = 0.0,
        var radius: Int = 0,
        var valuePrice: Double = 0.0,
        var id: Long = 0L
): BaseDTO()