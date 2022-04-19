package com.farma.poc.features.login.presentation

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.farma.poc.R
import com.farma.poc.core.base.BaseViewModel
import com.farma.poc.core.navigation.RouterNavigationEnum
import com.farma.poc.core.utils.safeLet
import com.farma.poc.features.login.data.models.ResponseLoginDTO
import com.farma.poc.features.login.data.repository.LoginRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(private val loginRepository: LoginRepository, context: Context) :
    BaseViewModel(context = context) {

    private var showLoadingLogin = MutableLiveData<Boolean>(false)
    var statusShowLoading: LiveData<Boolean> = showLoadingLogin


    var passwordText = mutableStateOf("")
    var emailText = mutableStateOf("")

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


    fun login() {
        safeLet(emailText.value, passwordText.value, onResult = { emailText, passwordText ->
            authenticate(emailText, passwordText)
        }, onFailure = {
            // SHOW ERROR EMAIL OR PASSWORD EMPTY
        })

    }

    fun redirectToSingUp() {

    }

    private fun authenticate(email: String, password: String) {
        viewModelScope.launch {
            loginRepository.authenticateUser(
                email = email,
                password = password,
                onSuccessData = { responseLogin ->
                    val autenticateUser = { data: ResponseLoginDTO? ->
                        authenticateUserWhereSuccessGetToken(
                            data = data,
                            onRedirect = {
                                redirectHomeApp()
                            }
                        )
                    }
                    autenticateUser(responseLogin)
                },
                onFailureError = {
                    showErrorFeedBack()
                },
                onShowLoading = {
                    showLoadingLogin.postValue(it)
                }
            )
        }
    }

    private fun authenticateUserWhereSuccessGetToken(
        data: ResponseLoginDTO?,
        onRedirect: (() -> Unit)? = null
    ) {
        safeLet(data?.bearerToken, data?.dataExpires, onResult = { bearerToken, dataExpires ->
            setupAcronymnUserAuthenticated { firstLetter ->
                viewModelScope.launch {
                    getDataStoreConfig().setAcronymUserFlow(firstLetter)
                    getDataStoreConfig().setSharedTokenSession(bearerToken)
                    getDataStoreConfig().setSharedTimeSession(dataExpires)
                }

            }
            onRedirect?.invoke()
        })
    }

    private fun setupAcronymnUserAuthenticated(firstLetter: (String) -> Unit) {
        safeLet(emailText.value, passwordText.value, onResult = { emailText, _ ->
            firstLetter.invoke(emailText.substring(0))
        })
    }

    private fun showErrorFeedBack() {
        viewModelScope.launch {
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