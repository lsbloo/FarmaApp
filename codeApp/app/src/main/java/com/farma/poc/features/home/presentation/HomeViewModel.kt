package com.farma.poc.features.home.presentation

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.farma.poc.core.base.BaseViewModel
import com.farma.poc.core.firebase.downloadUriImageFirebase
import com.farma.poc.core.navigation.RouterNavigationEnum
import com.farma.poc.core.utils.dto.ProductDTO
import com.farma.poc.features.home.data.models.CategoryDTO
import com.farma.poc.features.home.data.models.ItemsHomeDTO
import com.farma.poc.features.home.data.repository.HomeRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.ArrayList

class HomeViewModel(private val homeRepository: HomeRepository, context: Context) :
    BaseViewModel(context) {

    var itemsHome = mutableStateOf(ItemsHomeDTO())
    var onShouldLoading = mutableStateOf(false)

    var imagesCategories = mutableStateListOf<DataImage>()
    var imagesHighLights = mutableStateListOf<DataImage>()
    var imagesProductsHighLights = mutableStateListOf<DataImage>()

    data class DataImage(var imageUri: Uri? = null, var name: String? = null)


    fun getItemsHome() {
        imagesCategories.clear()
        viewModelScope.launch {
            homeRepository.getItemsHome(
                onSuccessData = { items ->
                    items?.let { itemsHome.value = it }
                    items?.categories?.forEach { itemsCategorie ->
                        setupImageCategorie(itemsCategorie.urlImage, itemsCategorie.name)
                    }
                    items?.highLights?.forEach { highLights ->
                        setupImageHighLight(highLights.image,highLights.name)
                    }
                    items?.productsHighLight?.forEach { productsHighLight ->
                        setupImagesProductsHighLights(productsHighLight.image,productsHighLight.name)
                    }
                },
                onShowLoading = {
                    onShouldLoading.value = it
                },
                onFailureError = {
                    getDataCached {
                        showToastNetworkUnavailable()
                    }
                },
                errorNetWorkNotAvailablex = {

                })
        }
    }

    private fun setupImagesProductsHighLights(imagePath: String?, nameHighLight: String?) {
        runBlocking {
            downloadUriImageFirebase(imagePath, onSuccess = {
                imagesProductsHighLights.add(DataImage(it, nameHighLight))
            }, onFailure = {})
        }
    }
    private fun setupImageHighLight(imagePath: String?, nameHighLight: String?) {
        runBlocking {
            downloadUriImageFirebase(imagePath, onSuccess = {
                imagesHighLights.add(DataImage(it, nameHighLight))
            }, onFailure = {})
        }
    }

    private fun setupImageCategorie(imagePath: String?, nameCategorie: String?) {
        runBlocking {
            downloadUriImageFirebase(imagePath, onSuccess = {
                imagesCategories.add(DataImage(it, nameCategorie))
            }, onFailure = {})
        }
    }

    fun getCurrentProductHighLightImage(nameHighLight: String?): Uri? {
        return imagesProductsHighLights.firstOrNull {
            it.name?.let { name -> name.contains(nameHighLight!!, ignoreCase = true) } == true
        }?.imageUri
    }

    fun getCurrentHighLightImage(nameHighLight: String?): Uri? {
        return imagesHighLights.firstOrNull {
            it.name?.let { name -> name.contains(nameHighLight!!, ignoreCase = true) } == true
        }?.imageUri
    }

    fun getCurrentCategorieImage(nameCategorie: String?): Uri? {
        return imagesCategories.firstOrNull {
            it.name?.let { name -> name.contains(nameCategorie!!, ignoreCase = true) } == true
        }?.imageUri
    }

    fun getItemsCategories(): ArrayList<CategoryDTO> {
        val listCategory = ArrayList<CategoryDTO>()
        itemsHome.value.categories?.forEach {
            listCategory.add(it)
        }
        return listCategory
    }

    fun getItemsProductHighLight(): ArrayList<ProductDTO> {
        val productsHighLight = ArrayList<ProductDTO>()
        itemsHome.value.productsHighLight?.forEach {
            productsHighLight.add(it)
        }
        return productsHighLight
    }

    fun getHighLights(): ArrayList<ProductDTO> {
        val productsHighLight = ArrayList<ProductDTO>()
        itemsHome.value.highLights?.forEach {
            productsHighLight.add(it)
        }
        return productsHighLight
    }

    private fun getDataCached(onFailureRecovery: () -> Unit) {
        viewModelScope.launch {
            homeRepository.getItemsHomeCached().collect { items ->
                items?.let {
                    itemsHome.value = it
                } ?: also {
                    onFailureRecovery.invoke()
                }
            }
        }
    }

    fun redirectToSettings() {
        routerNavigation?.navigateTo(RouterNavigationEnum.SETTINGS)
    }
}