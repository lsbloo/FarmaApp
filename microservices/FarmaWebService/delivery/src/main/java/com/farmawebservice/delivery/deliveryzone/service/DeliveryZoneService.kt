package com.farmawebservice.delivery.deliveryzone.service

import com.farmawebservice.delivery.address.repository.AddressRepository
import com.farmawebservice.delivery.base.BaseDTO
import com.farmawebservice.delivery.base.service.BaseService
import com.farmawebservice.delivery.base.validator.core.ValidatorBuilder
import com.farmawebservice.delivery.base.validator.resource.ResourceMessage
import com.farmawebservice.delivery.deliveryzone.model.DeliveryZone
import com.farmawebservice.delivery.deliveryzone.model.dto.DDeliveryZoneDTO
import com.farmawebservice.delivery.deliveryzone.model.dto.DeliveryZoneDTO
import com.farmawebservice.delivery.deliveryzone.repository.DeliveryZoneRepository
import com.farmawebservice.delivery.deliveryzone.validator.DeliveryZoneValidator
import com.farmawebservice.delivery.shop.model.dto.ShopStoreProductDTO
import com.farmawebservice.delivery.shop.repository.ShopRepository
import com.farmawebservice.delivery.user.model.User
import com.farmawebservice.delivery.user.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service

class DeliveryZoneService(
        @Autowired private val shopRepository: ShopRepository,
        @Autowired private val addressRepository: AddressRepository,
        @Autowired private val deliveryZoneRepository: DeliveryZoneRepository,
        @Autowired private val userRepository: UserRepository,
        @Autowired private val deliveryZoneValidator: DeliveryZoneValidator
) : BaseService() {


    private fun authClientId(dto: BaseDTO): User? {
        setClientId(dto.client_id_token)
        return authenticateUserClient(userRepository)
    }


    fun getAllDeliveryZoneForShop(dto: BaseDTO): ResourceMessage? {

        authClientId(dto)?.let {
            val resultShopExists = ValidatorBuilder<BaseDTO>().apply(
                    this.deliveryZoneValidator.validateIfShopStoreExists()
            ).validate(dto)

            if (resultShopExists.ok()) {

                val shop = this.shopRepository.findByClientIdToken(dto.client_id_token)
                return ResourceMessage().apply {
                    this.isError = false
                    this.messageValidator = resultShopExists.resourceMessage.messageValidator
                    this.descriptionValidator = "All DeliveryZones recovery"
                    this.responseDTO = shop.first().deliveryZones
                }
            } else {
                return ResourceMessage().apply {
                    this.isError = true
                    this.messageValidator = resultShopExists.resourceMessage.messageValidator
                    this.responseDTO = null
                }
            }
        }
        return null
    }

    fun deleteDeliveryZoneForShop(dto: DDeliveryZoneDTO): ResourceMessage? {
        authClientId(dto)?.let {

            val resultShopExists = ValidatorBuilder<BaseDTO>().apply(
                    this.deliveryZoneValidator.validateIfShopStoreExists()
            ).validate(dto)

            if (resultShopExists.ok()) {
                val deliveryZone = this.deliveryZoneRepository.findById(dto.id_delivery_zone)
                if (deliveryZone.isPresent) {
                    val shop = this.shopRepository.findByClientIdToken(dto.client_id_token)
                    this.deliveryZoneRepository.deleteShopDeliveryZone(shop.first().id, dto.id_delivery_zone)
                    this.deliveryZoneRepository.delete(deliveryZone.get())
                    return ResourceMessage().apply {
                        this.isError = false
                        this.messageValidator = resultShopExists.resourceMessage.messageValidator
                        this.descriptionValidator = "DeliveryZone has removed"
                        this.responseDTO = deliveryZone
                    }
                }
            } else {
                return ResourceMessage().apply {
                    this.isError = true
                    this.messageValidator = resultShopExists.resourceMessage.messageValidator
                    this.responseDTO = null
                }
            }
        }
        return null
    }

    fun createDeliveryZoneForShop(dto: DeliveryZoneDTO): ResourceMessage? {
        setClientId(dto.client_id_token)

        authClientId(dto)?.let {
                val resultHasDeliveryZone = ValidatorBuilder<DeliveryZoneDTO>().apply(
                        this.deliveryZoneValidator.validateIfHasDeliveryZoneForShopStore()

                ).validate(dto)

                if (resultHasDeliveryZone.ok()) {
                    val dZone = DeliveryZone().apply {
                        this.latitude = dto.latitude
                        this.longitude = dto.longitude
                        this.radius = dto.radius
                        this.valuePrice = dto.valuePrice
                    }

                    val dZoneAtCreat = this.deliveryZoneRepository.save(dZone)

                    val shop = this.shopRepository.findByClientIdToken(dto.client_id_token)

                    this.deliveryZoneRepository.saveShopDeliveryZone(shop.first().id, dZoneAtCreat.id)

                    return ResourceMessage().apply {
                        this.isError = false
                        this.messageValidator = "Delivery Zone at created"
                        this.responseDTO = dZoneAtCreat
                    }
                } else {
                    return ResourceMessage().apply {
                        this.isError = true
                        this.messageValidator = resultHasDeliveryZone.resourceMessage.messageValidator
                        this.responseDTO = null
                    }
                }
        }
        return null
    }
}