package com.farma.poc.features.home.presentation

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.farma.poc.core.base.BaseViewModel
import com.farma.poc.core.navigation.RouterNavigationEnum
import com.farma.poc.features.home.data.models.CategoryDTO
import com.farma.poc.features.home.data.models.ItemsHomeDTO
import com.farma.poc.features.home.data.repository.HomeRepository
import kotlinx.coroutines.launch
import java.util.ArrayList

class HomeViewModel(private val homeRepository: HomeRepository, context: Context) :
    BaseViewModel(context) {

    var itemsHome = mutableStateOf(ItemsHomeDTO())
    var onShouldLoading = mutableStateOf(false)


    fun getItemsHome() {
        viewModelScope.launch {
            homeRepository.getItemsHome(
                onSuccessData = { items ->
                    items?.let { itemsHome.value = it }
                },
                onShowLoading = {
                    onShouldLoading.value = it
                },
                onFailureError = {
                    getDataCached {
                        // show Error
                    }
                },
                errorNetWorkNotAvailablex = {

                })
        }
    }

    fun getItensCategories(itemsHomeDTO: ItemsHomeDTO): ArrayList<CategoryDTO> {
        val listCategory = ArrayList<CategoryDTO>()
        itemsHomeDTO.categories?.forEach {
            listCategory.add(it)
        }
        return listCategory
    }
    private fun getDataCached(onFailureRecovery: () -> Unit){
        viewModelScope.launch {
            homeRepository.getItemsHomeCached().collect {
                items ->
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