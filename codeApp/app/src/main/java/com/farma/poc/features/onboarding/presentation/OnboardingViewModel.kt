package com.farma.poc.features.onboarding.presentation

import android.content.Context
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

class OnboardingViewModel(private val onboardingRepository: OnboardingRepository, context: Context) :
    BaseViewModel(context) {

    var onboardingDataSet: OnboardingDTO? by mutableStateOf(null)
    var stateViewPageIndex by mutableStateOf(0)

    var showErrorNetwork = mutableStateOf(false)
    init {
        getOnboardingDataScreen()
    }

    private fun getOnboardingDataScreen() {
        viewModelScope.launch {
            onboardingRepository.getOnboardingData(
                onSuccessData = { it ->
                    it.takeIf { SUCCESS_DATA }.apply {
                        setupDataSet()
                    }
                },
                onFailureError = {
                    setupDataSet { hasRecoveryData -> trySetupDataWithRecoveryLocal(hasRecoveryData) }
                },
                onShowLoading = {

                },
                onNetworkError = {
                    CoroutineScope(Dispatchers.Main).launch {
                        showErrorNetwork.value = true
                    }
                }
            )
        }

    }

    private fun setupDataSet(onRecovery: ((Boolean) -> Unit?)? = null){
        CoroutineScope(Dispatchers.Main).launch {
            onboardingRepository.getOnboardingFlow().collect {
                it?.let { onboardingData ->
                    onboardingDataSet = onboardingData
                    onRecovery?.invoke(true)
                }?: also {
                    onRecovery?.invoke(false)
                }
            }
        }
    }

    private fun trySetupDataWithRecoveryLocal(hasDataRecovery: Boolean) {
        hasDataRecovery.takeIf { !SUCCESS_DATA }.apply {
            setupDataSet()
        }?: also {
            // show error
        }
    }

    fun animateToPage(index: Int, redirectLogin: (() -> Unit) ? = null) {
        if(index == 2) {
            redirectLogin?.invoke()
        } else {
            stateViewPageIndex = index
        }
    }

    fun redirectToLogin() {
        this.routerNavigation?.navigateTo(RouterNavigationEnum.LOGIN)
    }

    companion object {
        private const val SUCCESS_DATA = true
    }
}