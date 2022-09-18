package com.farmawebservice.delivery.deliveryzone.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity(name = "delivery_zone")
class DeliveryZone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    var latitude: Double = 0.0

    var longitude: Double = 0.0

    var radius: Int = 0

    var valuePrice: Double = 0.0

}