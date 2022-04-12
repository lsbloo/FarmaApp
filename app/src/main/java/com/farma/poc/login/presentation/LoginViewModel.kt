package com.farma.poc.login.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.farma.poc.R
import com.farma.poc.core.base.BaseViewModel
import com.farma.poc.core.navigation.RouterNavigation
import com.farma.poc.core.navigation.RouterNavigationEnum
import com.farma.poc.login.data.models.ResponseLoginDTO
import com.farma.poc.login.data.repository.LoginRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(private val loginRepository: LoginRepository) : BaseViewModel() {

    private val _authenticateUser = MutableLiveData<ResponseLoginDTO?>()
    var authenticateLogin: LiveData<ResponseLoginDTO?> = _authenticateUser

    private var showLoadingLogin = MutableLiveData<Boolean>(false)
    var statusShowLoading: LiveData<Boolean> = showLoadingLogin


    var showErrorFeedBack = mutableStateOf(false)

    var stateEyeLogin = mutableStateOf(true)

    fun changeStateEyeLogin(state: Boolean) {
        stateEyeLogin.value = state
    }

    var iconLogin: Int = 0
        get() = if (stateEyeLogin.value) {
            R.drawable.ic_closed_eye
        } else {
            R.drawable.ic_open_eye
        }


    suspend fun login() {
        loginRepository.authenticateUser(
            onSuccessLiveData = {
                _authenticateUser.postValue(it.value)
                redirectHomeApp()
            },
            onFailureError = {
                showErrorFeedBack()
            },
            onShowLoading = {
                showLoadingLogin.postValue(it)
            }
        )
    }


    private fun showErrorFeedBack() {
        CoroutineScope(Dispatchers.Main).launch {
            showErrorFeedBack.value = true
        }
    }

    private fun redirectHomeApp() {
        CoroutineScope(Dispatchers.Main).launch {
            routerNavigation?.navigateTo(RouterNavigationEnum.HOME)
        }
    }

    companion object {
        private const val TIME_HIDE_FEEDBACK_ERROR = 3000L
    }
}