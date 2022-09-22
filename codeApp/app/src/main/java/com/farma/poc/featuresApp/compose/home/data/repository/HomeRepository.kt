package com.farma.poc.featuresApp.compose.home.data.repository

import com.farma.poc.featuresApp.compose.home.data.dao.HomeDAO
import com.farma.poc.featuresApp.compose.home.data.models.ItemsHomeDTO
import com.farma.poc.featuresApp.compose.home.data.task.GetHomeItemsTask
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.onStart
import okhttp3.ResponseBody

class HomeRepository(private val getHomeItemsTask: GetHomeItemsTask, private val homeDAO: HomeDAO) {


    suspend fun getItemsHome(
        onSuccessData: (ItemsHomeDTO?) -> Unit,
        onFailureError: (ResponseBody?) -> Unit,
        onShowLoading: (Boolean) -> Unit,
        errorNetWorkNotAvailablex: () -> Unit,
    ) {
        getHomeItemsTask.call(null, callback = { onSuccess, onFailure, onShouldLoading ->
            onSuccess?.data?.let { data ->
                onSuccessData.invoke(data)
                homeDAO.saveItemsHome(data)
            }

            onFailure?.data?.let {
                onFailureError.invoke(it)
            }
            onShouldLoading?.let {
                onShowLoading.invoke(it)
            }
        }, errorNetWorkNotAvailable = {
            errorNetWorkNotAvailablex.invoke()
        })
    }


    suspend fun getItemsHomeCached() = homeDAO.getItemsHomeCached(SINGLE_ID_ITEM_HOME).onStart { delay(1000) }


    companion object {
        private const val SINGLE_ID_ITEM_HOME = 1L
    }

}