package com.farma.poc.features.home.data.task

import android.content.Context
import com.farma.poc.core.base.BaseNetworkTaskImpl
import com.farma.poc.core.config.network.ResultTask
import com.farma.poc.features.home.data.api.HomeAPI
import com.farma.poc.features.home.data.models.CategoryDTO
import com.farma.poc.features.home.data.models.ItemsHomeDTO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.ResponseBody

class GetHomeItemsTask(private val homeAPI: HomeAPI, context: Context) :
    BaseNetworkTaskImpl<Any, ItemsHomeDTO, ResponseBody>(context) {


    override suspend fun call(
        t: Any?,
        callback: (ResultTask.OnSuccess<ItemsHomeDTO>?, ResultTask.OnFailure<ResponseBody>?, onShouldLoading: Boolean?) -> Unit,
        errorNetWorkNotAvailable: () -> Unit,
    ) {
        if (hasNetworkAvailable) {
            callback.invoke(null, null, true)
            CoroutineScope(Dispatchers.IO).launch {
                val result = homeAPI.getItemsHome()
                if (result.isSuccessful) {
                    callback(ResultTask.OnSuccess(data = result.body()), null, false)
                } else {
                    callback(null, ResultTask.OnFailure(data = result.errorBody()), false)
                }
            }
        } else {
            errorNetWorkNotAvailable.invoke()
        }
    }

}