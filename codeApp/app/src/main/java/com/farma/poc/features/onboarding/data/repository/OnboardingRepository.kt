package com.farma.poc.features.onboarding.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import com.farma.poc.features.onboarding.data.dao.OnboardingDAO
import com.farma.poc.features.onboarding.data.models.OnboardingDTO
import com.farma.poc.features.onboarding.data.task.OnboardingTask
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.delayFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import java.util.concurrent.Flow

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

    fun getOnboardingFlow() = onboardingDAO.getOnboardingDataSet(SINGLE_ID_ONBOARDING).onStart { delay(1000) }


    companion object {
        private const val SINGLE_ID_ONBOARDING = 1
    }
}