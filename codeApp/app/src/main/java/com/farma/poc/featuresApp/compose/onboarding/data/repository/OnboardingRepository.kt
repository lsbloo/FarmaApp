package com.farma.poc.featuresApp.compose.onboarding.data.repository

import com.farma.poc.featuresApp.compose.onboarding.data.dao.OnboardingDAO
import com.farma.poc.featuresApp.compose.onboarding.data.task.OnboardingTask
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.onStart
import okhttp3.ResponseBody

class OnboardingRepository(
    private val onboardingTask: OnboardingTask,
    private val onboardingDAO: OnboardingDAO
) {


    suspend fun getOnboardingData(
        onSuccessData: (Boolean) -> Unit,
        onFailureError: (ResponseBody?) -> Unit,
        onShowLoading: (Boolean) -> Unit,
        onNetworkError: () -> Unit,
    ) {
        onboardingTask.call(
            callback = { onSuccess, onFailure, onShouldLoading ->
                onSuccess?.data?.let {
                    onboardingDAO.insertOnboardingDTO(it)
                    onSuccessData.invoke(true)
                }
                onFailure?.data?.let {
                    onFailureError.invoke(it)
                }

                onShouldLoading?.let {
                    onShowLoading.invoke(it)
                }
            },
            errorNetWorkNotAvailable = {
                onNetworkError.invoke()
            }
        )
    }

    suspend fun getOnboardingFlow() =
        onboardingDAO.getOnboardingDataSet(SINGLE_ID_ONBOARDING).onStart { delay(1000) }


    companion object {
        private const val SINGLE_ID_ONBOARDING = 1
    }
}