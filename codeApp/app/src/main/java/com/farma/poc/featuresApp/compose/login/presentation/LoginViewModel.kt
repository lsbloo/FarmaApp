package com.farma.poc.featuresApp.compose.login.presentation

import android.content.Context
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.biometric.BiometricPrompt
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.farma.poc.R
import com.farma.poc.core.base.BaseActivity
import com.farma.poc.core.base.BaseViewModel
import com.farma.poc.core.config.biometric.OnAuthenticationBiometric
import com.farma.poc.core.config.biometric.SetupBiometricInfo.Companion.getBiometric
import com.farma.poc.core.config.biometric.TypeAuthentication
import com.farma.poc.core.navigation.RouterNavigationEnum
import com.farma.poc.core.utils.safeLet
import com.farma.poc.core.utils.typeValidator.PojoValidator
import com.farma.poc.featuresApp.compose.address.data.models.AddressDTO
import com.farma.poc.featuresApp.compose.address.data.repository.AddressRepository
import com.farma.poc.featuresApp.compose.login.data.models.ResponseLoginDTO
import com.farma.poc.featuresApp.compose.login.data.repository.LoginRepository
import com.farma.poc.featuresApp.compose.login.validators.interfaces.LoginValidator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginRepository: LoginRepository,
    private val addressRepository: AddressRepository,
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

    var hasFlagShowBiometric = mutableStateOf(false)

    fun changeStateEyeLogin(state: Boolean) {
        stateEyeLogin.value = state
    }

    var iconLogin: Int = 0
        get() = if (stateEyeLogin.value) {
            R.drawable.ic_closed_eye
        } else {
            R.drawable.ic_open_eye
        }


    init {
        getStatusShowBiometric()
    }

    private fun getStatusShowBiometric() {
        viewModelScope.launch {
            getDataStoreConfig().sharedFlagShowBiometric.collect {
                hasFlagShowBiometric.value = it
            }
        }
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
                            email = email,
                            onRedirect = {
                                validateIfHasAddress { hasAddress ->
                                    if (hasAddress) {
                                        redirectHomeApp()
                                    } else {
                                        redirectToAddressPage()
                                    }
                                }
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

    private fun redirectToAddressPage() {
        viewModelScope.launch {
            routerNavigation?.navigateTo(RouterNavigationEnum.ADDRESS)
        }
    }

    private fun authenticateUserWhereSuccessGetToken(
        data: ResponseLoginDTO?,
        email: String,
        onRedirect: (() -> Unit)? = null
    ) {
        safeLet(data?.bearerToken, data?.type, onResult = { bearerToken, dataType ->
            setupAcronymnUserAuthenticated { firstLetter ->
                viewModelScope.launch {
                    getDataStoreConfig().apply {
                        setSharedEmailUser(email)
                        setAcronymUserFlow(firstLetter)
                        setSharedTokenSession(bearerToken)
                        setTypeToken(dataType)
                        setClientIdToken(data?.clientIdToken)
                    }
                }

            }
            onRedirect?.invoke()
        })
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun authenticate(activity: BaseActivity) {
        if (hasFlagShowBiometric.value) {
            getBiometric(activity).apply {
                getAvailableBiometricInDevice(
                    onBiometricSuccess = {
                        setup(
                            activity.getString(R.string.label_title_biometric),
                            activity.getString(R.string.label_subtitle_biometric),
                            object : OnAuthenticationBiometric {
                                override fun onAuthenticateError(
                                    error: CharSequence
                                ) {
                                    Log.d("ERROR_BIOMETRIC", "" + error)
                                    Toast.makeText(
                                        activity,
                                        activity.getText(R.string.label_error_authenticate_biometric),
                                        Toast.LENGTH_SHORT
                                    )
                                }

                                override fun onAuthenticateFailed() {
                                    Log.d("FAILED_BIOMETRIC", "GENERAL TYPE ERROR")
                                    Toast.makeText(
                                        activity,
                                        activity.getText(R.string.label_error_authenticate_biometric),
                                        Toast.LENGTH_SHORT
                                    )
                                }

                                override fun onAuthenticateSuccess(
                                    result: BiometricPrompt.AuthenticationResult
                                ) {
                                    Log.d("SUCCESS_BIOMETRIC", "AUTHENTICATION SUCESSFUL")
                                    authenticateWithBiometricOrCredential(result.authenticationType)
                                }

                            })
                    },
                    onBiometricErrorUnsupported = {
                        login()
                    },
                    onBiometricErrorHardware = {
                        login()
                    },
                    onBiometricCreateCredentials = {
                        login()
                    },
                    onBiometricNotHasHardware = {
                        login()
                    },
                )
            }
        } else {
            login()
        }
    }

    private fun authenticateWithBiometricOrCredential(typeAuth: Int) {
        val typeAuthentication =
            if (typeAuth == TypeAuthentication.AUTHENTICATION_BIOMETRIC.value) {
                TypeAuthentication.AUTHENTICATION_BIOMETRIC
            } else {
                TypeAuthentication.AUTHENTICATION_CREDENTIAL
            }

        // TODO METHOD
        redirectHomeApp()
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
        viewModelScope.launch {
            routerNavigation?.navigateTo(RouterNavigationEnum.HOME)
        }
    }


    private fun validateIfHasAddress(hasAddresses: (Boolean) -> Unit) {
        viewModelScope.launch {
            getClientToken {
                viewModelScope.launch {
                    addressRepository.getAddresses(
                        onSuccessData = { dataAddress ->
                            dataAddress?.let {
                                if (it.isEmpty()) {
                                    hasAddresses.invoke(false)
                                } else {
                                    hasAddresses.invoke(true)
                                }
                            }?: also {
                                hasAddresses.invoke(false)
                            }
                        }, onShowLoading = {}, errorNetWorkNotAvailablex = {},
                        address = AddressDTO().apply { client_id_token = it }
                    )
                }
            }
        }
    }

    companion object {
        private const val TIME_HIDE_FEEDBACK_ERROR = 3000L
    }
}