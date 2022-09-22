package com.farma.poc.featuresApp.compose.onboarding.presentation

import android.content.Context
import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.farma.poc.core.base.BaseViewModel
import com.farma.poc.core.firebase.downloadUriImageFirebase
import com.farma.poc.core.navigation.RouterNavigationEnum
import com.farma.poc.featuresApp.compose.onboarding.data.models.OnboardingDTO
import com.farma.poc.featuresApp.compose.onboarding.data.repository.OnboardingRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class OnboardingViewModel(
    private val onboardingRepository: OnboardingRepository,
    context: Context
) :
    BaseViewModel(context) {

    var onboardingDataSet: OnboardingDTO? by mutableStateOf(null)
    var stateViewPageIndex by mutableStateOf(0)

    var showErrorNetwork = mutableStateOf(false)

    var imagesOnboarding = mutableListOf<Uri>()

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
                    showToastNetworkUnavailable()
                }
            )
        }

    }

    private fun setupDataSet(onRecovery: ((Boolean) -> Unit?)? = null) {
        CoroutineScope(Dispatchers.Main).launch {
            onboardingRepository.getOnboardingFlow().collect {
                it?.let { onboardingData ->
                    runBlocking {
                        onboardingData.onboardingScreen?.forEach { items ->
                            downloadUriImageFirebase(items.image, onSuccess = {
                                imagesOnboarding.add(it)
                            }, onFailure = {})
                        }
                        onboardingDataSet = onboardingData
                        onRecovery?.invoke(true)
                    }
                } ?: also {
                    onRecovery?.invoke(false)
                }
            }
        }
    }

    fun getImageOnboarding(path: String, onSuccess: (Uri) -> Unit) {
        downloadUriImageFirebase(pathString = path, onSuccess = {
            onSuccess.invoke(it)
        }, onFailure = {})
    }

    private fun trySetupDataWithRecoveryLocal(hasDataRecovery: Boolean) {
        hasDataRecovery.takeIf { !SUCCESS_DATA }.apply {
            setupDataSet()
        } ?: also {
            // show error
        }
    }

    fun animateToPage(index: Int, redirectLogin: (() -> Unit)? = null) {
        if (index == 2) {
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