package com.farmawebservice.delivery.home.service

import com.farmawebservice.delivery.address.service.AddressService
import com.farmawebservice.delivery.base.connector.Foo.RESOURCE_CALC_ZONE_GEOLOCATOR
import com.farmawebservice.delivery.base.connector.Foo.RESOURCE_OBTAIN_NAME_USER
import com.farmawebservice.delivery.base.extensions.FarmaWebServiceExtensions.convertToDTO
import com.farmawebservice.delivery.base.extensions.FarmaWebServiceExtensions.convertToDeliveryZoneDTO
import com.farmawebservice.delivery.base.extensions.FarmaWebServiceExtensions.getOnlyHighLights
import com.farmawebservice.delivery.base.extensions.FarmaWebServiceExtensions.getShopProductHighLights
import com.farmawebservice.delivery.base.service.BaseService
import com.farmawebservice.delivery.base.service.HomeAppService
import com.farmawebservice.delivery.base.validator.resource.ResourceMessage
import com.farmawebservice.delivery.categories.service.CategoriesService
import com.farmawebservice.delivery.home.model.dto.GeoLocatorDeliveryZoneDTO
import com.farmawebservice.delivery.home.model.dto.HomeItemDTO
import com.farmawebservice.delivery.home.model.dto.ResponseHomeItemDTO
import com.farmawebservice.delivery.home.model.dto.ShopStoreGeoLocatorDTO
import com.farmawebservice.delivery.products.service.ProductsService
import com.farmawebservice.delivery.shop.service.ShopService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class HomeService(
        @Autowired private val shopService: ShopService,
        @Autowired private val addressService: AddressService,
        @Autowired private val productService: ProductsService,
        @Autowired private val categoryService: CategoriesService
) : HomeAppService(shopService) {


    fun getAllItemsFromHome(accessToken: String, dto: HomeItemDTO, onResponse: (ResourceMessage?) -> Unit) {
        getHomeItemsByUser(accessToken, dto, onSuccess = {
            onResponse.invoke(it)
        }, onFailure = {
            onResponse.invoke(it)
        })
    }

    fun getAllProductsByShop(accessToken: String, dto: HomeItemDTO, onResponse: (ResourceMessage?) -> Unit) {
        getShopByUser(accessToken, dto, onResult = {
            val responseMessage = ResourceMessage().apply {
                this.isError = false
                this.messageValidator = "All productShops for shop returned"
                this.descriptionValidator = "Recovery Items of shop: ${it.id}"
                this.responseDTO = it.productsShop
            }
            onResponse.invoke(responseMessage)
        }, onError = {
            val responseMessage = ResourceMessage().apply {
                this.isError = true
                this.messageValidator = "Error For recovery item of shop"
                this.responseDTO = null
            }

            onResponse.invoke(responseMessage)
        })
    }

    fun getAllCategoriesByShop(accessToken: String, dto: HomeItemDTO, onResponse: (ResourceMessage?) -> Unit) {
        getShopByUser(accessToken, dto, onResult = {
            val responseMessage = ResourceMessage().apply {
                this.isError = false
                this.messageValidator = "All Categories for shop returned"
                this.descriptionValidator = "Recovery Items of shop: ${it.id}"
                this.responseDTO = it.categoryList.convertToDTO()
            }
            onResponse.invoke(responseMessage)
        }, onError = {
            val responseMessage = ResourceMessage().apply {
                this.isError = true
                this.messageValidator = "Error For recovery item of shop"
                this.responseDTO = null
            }

            onResponse.invoke(responseMessage)
        })
    }

    fun getAllProductsHighLightByShop(accessToken: String, dto: HomeItemDTO, onResponse: (ResourceMessage?) -> Unit) {
        getShopByUser(accessToken, dto, onResult = {
            val responseMessage = ResourceMessage().apply {
                this.isError = false
                this.messageValidator = "All ProductHighLights for shop returned"
                this.descriptionValidator = "Recovery Items of shop: ${it.id}"
                this.responseDTO = it.shopProductHighLights.getShopProductHighLights()
            }
            onResponse.invoke(responseMessage)
        }, onError = {
            val responseMessage = ResourceMessage().apply {
                this.isError = true
                this.messageValidator = "Error For recovery item of shop"
                this.responseDTO = null
            }

            onResponse.invoke(responseMessage)
        })
    }
}