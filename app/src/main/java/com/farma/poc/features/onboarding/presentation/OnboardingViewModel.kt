package com.farma.poc.features.onboarding.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.farma.poc.core.base.BaseViewModel
import com.farma.poc.core.navigation.RouterNavigationEnum
import com.farma.poc.features.onboarding.data.models.OnboardingDTO
import com.farma.poc.features.onboarding.data.repository.OnboardingRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class OnboardingViewModel(private val onboardingRepository: OnboardingRepository) :
    BaseViewModel() {

    var onboardingDataSet: OnboardingDTO? by mutableStateOf(null)

    init {
        getOnboardingDataScreen()
    }

    fun getOnboardingDataScreen() {
        viewModelScope.launch {
            onboardingRepository.getOnboardingData(
                onSuccessData = { it ->
                    if(it) { setupDataSet() }
                },
                onFailureError = {
                    setupDataSet {
                        if(!it) {
                            // show Error
                        }
                    }
                },
                onShowLoading = {},
            )
        }

    }

    private fun setupDataSet(onRecovery: ((Boolean) -> Unit?)? = null){
        CoroutineScope(Dispatchers.Main).launch {
            onboardingRepository.getOnboardingFlow().collect {
                if(it == null) {
                    onRecovery?.invoke(false)
                } else {
                    onboardingDataSet = it
                    onRecovery?.invoke(true)
                }
            }
        }
    }

    fun redirectToLogin() {
        this.routerNavigation?.navigateTo(RouterNavigationEnum.LOGIN)
    }
}