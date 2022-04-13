package com.farma.poc.features.home.data.task

import com.farma.poc.core.base.BaseNetworkTaskImpl
import com.farma.poc.core.config.network.ResultTask
import com.farma.poc.features.home.data.api.HomeAPI
import com.farma.poc.features.home.data.models.CategoryDTO
import okhttp3.ResponseBody

class HomeCategoryTask(private val homeAPI: HomeAPI) :
    BaseNetworkTaskImpl<CategoryDTO, ResponseBody>() {


    override suspend fun call(
        t: CategoryDTO?,
        callback: (ResultTask.OnSuccess<CategoryDTO>?, ResultTask.OnFailure<ResponseBody>?, onShouldLoading: Boolean?) -> Unit
    ) {
        TODO("Not yet implemented")
    }

}