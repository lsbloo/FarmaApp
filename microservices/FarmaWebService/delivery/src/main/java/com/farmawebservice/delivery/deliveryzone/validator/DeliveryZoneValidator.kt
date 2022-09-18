package com.farmawebservice.delivery.deliveryzone.validator

import com.farmawebservice.delivery.address.repository.AddressRepository
import com.farmawebservice.delivery.base.BaseDTO
import com.farmawebservice.delivery.base.validator.core.Result
import com.farmawebservice.delivery.base.validator.core.Validator
import com.farmawebservice.delivery.deliveryzone.model.dto.DeliveryZoneDTO
import com.farmawebservice.delivery.deliveryzone.repository.DeliveryZoneRepository
import com.farmawebservice.delivery.shop.repository.ShopRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class DeliveryZoneValidator {

    @Autowired
    private lateinit var shopRepository: ShopRepository

    @Autowired
    private lateinit var addressRepository: AddressRepository

    @Autowired
    private lateinit var deliveryZoneRepository: DeliveryZoneRepository


    fun validateIfShopStoreExists(): Validator<BaseDTO> {
        return Validator<BaseDTO> { result, deliveryZoneDTO ->

            val shop = this.shopRepository.findByClientIdToken(deliveryZoneDTO.client_id_token)
            shop?.let {
                result.ok()
            } ?: also {
                result.error("shop Store dont found")
            }

        }
    }


    fun validateIfHasDeliveryZoneForShopStore(): Validator<DeliveryZoneDTO> {
        return Validator<DeliveryZoneDTO> { result, deliveryZoneDTO ->
            val deliveryZone = this.deliveryZoneRepository.findDeliveryZoneByLatitudeAndLongitudeAndRadius(
                    deliveryZoneDTO.latitude,
                    deliveryZoneDTO.longitude,
                    deliveryZoneDTO.radius
            )
            deliveryZone?.let {
                result.error("Has delivery Zone with latitude, longitude and radius")
                result.setResourceMessage("Has delivery Zone with latitude, longitude and radius", true)
            } ?: also {
                result.ok()
            }
        }
    }
}