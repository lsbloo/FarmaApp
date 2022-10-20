package com.farmawebservice.delivery.deliveryzone.repository

import com.farmawebservice.delivery.deliveryzone.model.DeliveryZone
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import javax.transaction.Transactional

@Repository
interface DeliveryZoneRepository : CrudRepository<DeliveryZone, Long> {


    fun findDeliveryZoneByLatitudeAndLongitudeAndRadius(latitude: Double, longitude: Double, radius: Int): DeliveryZone?

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "INSERT INTO shop_store_delivery_zones (shop_store_id,delivery_zones_id) VALUES (?1,?2)")
    fun saveShopDeliveryZone(shop_store_id: Long?, delivery_zone_id: Long?)

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "DELETE FROM shop_store_delivery_zones where shop_store_id=?1 and delivery_zones_id=?2")
    fun deleteShopDeliveryZone(shop_store_id: Long?, delivery_zone_id: Long?)
}