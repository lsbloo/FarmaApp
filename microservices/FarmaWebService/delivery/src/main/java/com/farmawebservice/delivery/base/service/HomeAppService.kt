package com.farmawebservice.delivery.base.service

import com.farmawebservice.delivery.base.connector.Foo
import com.farmawebservice.delivery.base.extensions.FarmaWebServiceExtensions.convertToDTO
import com.farmawebservice.delivery.base.extensions.FarmaWebServiceExtensions.convertToDeliveryZoneDTO
import com.farmawebservice.delivery.base.extensions.FarmaWebServiceExtensions.getOnlyHighLights
import com.farmawebservice.delivery.base.extensions.FarmaWebServiceExtensions.getShopProductHighLights
import com.farmawebservice.delivery.base.validator.resource.ResourceMessage
import com.farmawebservice.delivery.home.model.dto.GeoLocatorDeliveryZoneDTO
import com.farmawebservice.delivery.home.model.dto.HomeItemDTO
import com.farmawebservice.delivery.home.model.dto.ResponseHomeItemDTO
import com.farmawebservice.delivery.home.model.dto.ShopStoreGeoLocatorDTO
import com.farmawebservice.delivery.shop.model.ShopStore
import com.farmawebservice.delivery.shop.service.ShopService

open class HomeAppService(private val shopService: ShopService) : BaseService() {


    fun getShopByUser(accessToken: String, dto: HomeItemDTO, onResult: (ShopStore) -> Unit, onError: (Exception) -> Unit) {
        generateAShopStoreGeoLocatorDTO(
                onShopStoreGeoLocator = {
                    val geoLocatorDZone = GeoLocatorDeliveryZoneDTO(
                            homeItemDTO = dto,
                            shopStore = it
                    )
                    val response = authClient.sendMethodPost(Foo.RESOURCE_OBTAIN_NAME_USER, null, accessToken)
                    response?.let { dataResponse ->
                        val dataConverted = convertClientResponseToDTO(dataResponse)
                        dataConverted.responseDTO?.let { _ ->
                            try {
                                val dataGeoLocatorConverted = convertGeoLocatorResponseToDTO(geoLocatorClient.sendMethodPost(Foo.RESOURCE_CALC_ZONE_GEOLOCATOR, geoLocatorDZone))

                                if (dataGeoLocatorConverted != null) {

                                    val shopOffer = this.shopService.getShopById(dataGeoLocatorConverted.shopStore.idShopStore)
                                    shopOffer?.let { shopStore ->
                                        onResult.invoke(shopStore)
                                    }
                                }

                            } catch (e: Exception) {
                                onError.invoke(e)
                            }
                        }
                    }
                }
        )
    }

    fun getHomeItemsByUser(accessToken: String, dto: HomeItemDTO, onSuccess: (ResourceMessage) -> Unit,
                           onFailure: (ResourceMessage) -> Unit) {
        generateAShopStoreGeoLocatorDTO(
                onShopStoreGeoLocator = {
                    val geoLocatorDZone = GeoLocatorDeliveryZoneDTO(
                            homeItemDTO = dto,
                            shopStore = it
                    )
                    val response = authClient.sendMethodPost(Foo.RESOURCE_OBTAIN_NAME_USER, null, accessToken)
                    response?.let { dataResponse ->
                        val dataConverted = convertClientResponseToDTO(dataResponse)
                        dataConverted.responseDTO?.let { _ ->
                            try {
                                val dataGeoLocatorConverted = convertGeoLocatorResponseToDTO(geoLocatorClient.sendMethodPost(Foo.RESOURCE_CALC_ZONE_GEOLOCATOR, geoLocatorDZone))

                                if (dataGeoLocatorConverted != null) {

                                    val shopOffer = this.shopService.getShopById(dataGeoLocatorConverted.shopStore.idShopStore)
                                    shopOffer?.let { shopStore ->

                                        val responseHomeItemDTO = ResponseHomeItemDTO(
                                                categories = shopStore.categoryList.convertToDTO(),
                                                highLights = shopStore.shopProductHighLights.getOnlyHighLights(),
                                                productsHighLight = shopStore.shopProductHighLights.getShopProductHighLights()
                                        )

                                        val responseMessage = ResourceMessage().apply {
                                            this.isError = false
                                            this.messageValidator = "All Items for shop returned"
                                            this.descriptionValidator = "Recovery Items of shop: ${shopOffer.id}"
                                            this.responseDTO = responseHomeItemDTO
                                        }
                                        onSuccess.invoke(responseMessage)
                                    }
                                }

                            } catch (e: Exception) {
                                val responseMessage = ResourceMessage().apply {
                                    this.isError = true
                                    this.messageValidator = "Error For recovery item of shop"
                                    this.responseDTO = null
                                }
                                onFailure.invoke(responseMessage)
                            }
                        }
                    }
                }
        )
    }


    private fun generateAShopStoreGeoLocatorDTO(onShopStoreGeoLocator: (List<ShopStoreGeoLocatorDTO>) -> Unit) {
        val dListShopStoreGeoLocator = ArrayList<ShopStoreGeoLocatorDTO>()
        this.shopService.allShops.forEach { shopStore ->
            dListShopStoreGeoLocator.add(
                    ShopStoreGeoLocatorDTO(idShopStore = shopStore.id, deliveryZones = shopStore.deliveryZones.convertToDeliveryZoneDTO())
            )
        }
        onShopStoreGeoLocator.invoke(dListShopStoreGeoLocator)
    }
}