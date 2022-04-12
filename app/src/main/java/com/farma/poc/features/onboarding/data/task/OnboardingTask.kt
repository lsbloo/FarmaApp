package com.farma.poc.features.onboarding.data.task

import com.farma.poc.core.base.BaseNetworkTask
import com.farma.poc.core.base.BaseNetworkTaskImpl
import com.farma.poc.core.config.network.ResultTask
import com.farma.poc.features.onboarding.data.api.OnboardingAPI
import com.farma.poc.features.onboarding.data.models.OnboardingDTO
import okhttp3.ResponseBody

class OnboardingTask(private val onboardingAPI: OnboardingAPI): BaseNetworkTaskImpl<OnboardingDTO, ResponseBody>() {


    override suspend fun call(
        t: OnboardingDTO?,
        callback: (ResultTask.OnSuccess<OnboardingDTO>?, ResultTask.OnFailure<ResponseBody>?, onShouldLoading: Boolean?) -> Unit
    ) {

    }

}