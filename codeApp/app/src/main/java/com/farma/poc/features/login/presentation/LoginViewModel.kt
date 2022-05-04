package com.farma.poc.features.login.presentation

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.farma.poc.R
import com.farma.poc.core.base.BaseViewModel
import com.farma.poc.core.navigation.RouterNavigation
import com.farma.poc.core.navigation.RouterNavigationEnum
import com.farma.poc.core.utils.safeLet
import com.farma.poc.core.utils.typeValidator.PojoValidator
import com.farma.poc.features.login.data.models.ResponseLoginDTO
import com.farma.poc.features.login.data.repository.LoginRepository
import com.farma.poc.features.login.validators.interfaces.LoginValidator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginRepository: LoginRepository,
    private val loginValidator: LoginValidator<PojoValidator>,
    context: Context
) :
    BaseViewModel(context = context) {

    private var showLoadingLogin = MutableLiveData<Boolean>(false)
    var statusShowLoading: LiveData<Boolean> = showLoadingLogin


    var passwordText = mutableStateOf("")
    var emailText = mutableStateOf("")

    var showErrorFeedBack = mutableStateOf(false)
    var showErrorFeedBackEmptyCredentials = mutableStateOf(false)

    var stateEyeLogin = mutableStateOf(true)

    var messageErrorEmailValidator = mutableStateOf("")
    var messageErrorPasswordValidator = mutableStateOf("")

    var isErrorEmail = mutableStateOf(false)
    var isErrorPassword = mutableStateOf(false)

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
            loginValidator.validateCredentialsLogin(emailText, passwordText, onValidate = {
                it.data?.let { validator ->
                    validator.dontHasError?.let { result ->
                        if (result) {
                            messageErrorEmailValidator.value =
                                validator.dataMessages?.first()?.second.toString()

                            validator.dataMessages?.first()?.third?.let {
                                isErrorEmail.value = it
                            }
                            messageErrorPasswordValidator.value =
                                validator.dataMessages?.last()?.second.toString()

                            validator.dataMessages?.last()?.third?.let {
                                isErrorPassword.value = it
                            }
                        } else {
                            authenticate(emailText, passwordText)
                            showErrorFeedBackEmptyCredentials.value = false
                        }
                    }
                }
            })
        }, onFailure = {
            showErrorFeedBackEmptyCredentials.value = true
            showErrorFeedBackCredentials()
        })

    }

    fun redirectToSingUp(onRedirect: (() -> Unit)? = null) {
        routerNavigation?.navigateTo(router = RouterNavigationEnum.SINGUP)
        onRedirect?.invoke()
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

    private fun showErrorFeedBackCredentials() {
        showErrorFeedBackEmptyCredentials.value = true
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