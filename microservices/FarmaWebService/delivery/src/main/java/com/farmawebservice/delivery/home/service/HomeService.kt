package com.farmawebservice.delivery.home.service

import com.farmawebservice.delivery.address.service.AddressService
import com.farmawebservice.delivery.base.connector.Foo.RESOURCE_CALC_ZONE_GEOLOCATOR
import com.farmawebservice.delivery.base.connector.Foo.RESOURCE_OBTAIN_NAME_USER
import com.farmawebservice.delivery.base.extensions.FarmaWebServiceExtensions.convertToDeliveryZoneDTO
import com.farmawebservice.delivery.base.service.BaseService
import com.farmawebservice.delivery.base.validator.resource.ResourceMessage
import com.farmawebservice.delivery.categories.service.CategoriesService
import com.farmawebservice.delivery.home.model.dto.GeoLocatorDeliveryZoneDTO
import com.farmawebservice.delivery.home.model.dto.HomeItemDTO
import com.farmawebservice.delivery.home.model.dto.ShopStoreGeoLocatorDTO
import com.farmawebservice.delivery.products.service.ProductsService
import com.farmawebservice.delivery.settings.model.SettingsLabelDTO
import com.farmawebservice.delivery.shop.service.ShopService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class HomeService(
        @Autowired private val shopService: ShopService,
        @Autowired private val addressService: AddressService,
        @Autowired private val productService: ProductsService,
        @Autowired private val categoryService: CategoriesService
) : BaseService() {


    fun getAllItemsFromHome(accessToken: String, dto: HomeItemDTO): ResourceMessage? {
        generateAShopStoreGeoLocatorDTO(
                onShopStoreGeoLocator = {
                    val geoLocatorDZone = GeoLocatorDeliveryZoneDTO(
                            homeItemDTO = dto,
                            shopStore = it
                    )
                    val response = authClient.sendMethodPost(RESOURCE_OBTAIN_NAME_USER, null, accessToken)
                    response?.let { dataResponse ->
                        val dataConverted = convertClientResponseToDTO(dataResponse)
                        dataConverted.responseDTO?.let { _ ->
                            try {
                                val dataGeoLocatorConverted = convertGeoLocatorResponseToDTO(geoLocatorClient.sendMethodPost(RESOURCE_CALC_ZONE_GEOLOCATOR, geoLocatorDZone))

                                if(dataGeoLocatorConverted != null) {

                                    val shopOffer = this.shopService.getShopById(dataGeoLocatorConverted.shopStore.idShopStore)
                                    shopOffer?.let {
                                        shopStore ->

                                    }
                                }

                            }catch (e: Exception) {

                            }
                        }
                    }
                }
        )
        return null
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