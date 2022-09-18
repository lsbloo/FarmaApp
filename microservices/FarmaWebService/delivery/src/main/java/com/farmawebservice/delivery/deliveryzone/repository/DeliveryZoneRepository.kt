package com.farmawebservice.delivery.deliveryzone.repository

import com.farmawebservice.delivery.base.repository.FarmaWebRepository
import com.farmawebservice.delivery.deliveryzone.model.DeliveryZone
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface DeliveryZoneRepository : CrudRepository<DeliveryZone, Long>, FarmaWebRepository {


    fun findDeliveryZoneByLatitudeAndLongitudeAndRadius(latitude: Double, longitude: Double, radius: Int): DeliveryZone?

}