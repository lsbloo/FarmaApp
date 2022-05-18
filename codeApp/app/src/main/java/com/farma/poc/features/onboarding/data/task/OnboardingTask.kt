package com.farma.poc.features.onboarding.data.task

import android.content.Context
import com.farma.poc.core.base.BaseNetworkTaskImpl
import com.farma.poc.core.config.network.ResultTask
import com.farma.poc.features.onboarding.data.api.OnboardingAPI
import com.farma.poc.features.onboarding.data.models.OnboardingDTO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.ResponseBody

class OnboardingTask(private val onboardingAPI: OnboardingAPI, context: Context) :
    BaseNetworkTaskImpl<Any, OnboardingDTO, ResponseBody>(context) {


    override suspend fun call(
        t: Any?,
        callback: (ResultTask.OnSuccess<OnboardingDTO>?, ResultTask.OnFailure<ResponseBody>?, onShouldLoading: Boolean?) -> Unit,
        errorNetWorkNotAvailable: () -> Unit,
    ) {
        if (verifyIfHasNetworkAvailable()) {
            CoroutineScope(Dispatchers.IO).launch {
                callback.invoke(null, null, true)
                val result = onboardingAPI.getOnboarding()
                result.isSuccessful.let {
                    if (it) {
                        callback.invoke(ResultTask.OnSuccess(data = result.body()), null, false)
                    } else {
                        callback.invoke(
                            null,
                            ResultTask.OnFailure(data = result.errorBody()),
                            false
                        )
                    }
                }
            }
        } else {
            errorNetWorkNotAvailable.invoke()
        }
    }
}