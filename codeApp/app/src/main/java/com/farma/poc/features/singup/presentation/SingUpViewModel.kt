package com.farma.poc.features.singup.presentation

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.farma.poc.core.base.BaseViewModel
import com.farma.poc.core.navigation.RouterNavigationEnum
import com.farma.poc.core.utils.safeLet
import com.farma.poc.core.utils.typeValidator.PojoValidator
import com.farma.poc.features.singup.data.repository.SingUpRepository
import com.farma.poc.features.singup.validators.interfaces.SingUpValidator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SingUpViewModel(
    private val singUpRepository: SingUpRepository,
    context: Context,
    private val singUpValidator: SingUpValidator<PojoValidator>
) :
    BaseViewModel(context) {

    var email = mutableStateOf("")
    var password = mutableStateOf("")
    var name = mutableStateOf("")
    var cpf = mutableStateOf("")

    var showErrorFeedBackEmptyCredentials = mutableStateOf(false)
    var showErrorFeedBack = mutableStateOf(false)

    var messageErrorNameValidator = mutableStateOf("")
    var messageErrorEmailValidator = mutableStateOf("")
    var messageErrorCpfValidator = mutableStateOf("")
    var messageErrorPasswordValidator = mutableStateOf("")

    var isErrorName = mutableStateOf(false)
    var isErrorEmail = mutableStateOf(false)
    var isErrorCpf = mutableStateOf(false)
    var isErrorPassword = mutableStateOf(false)

    var shouldLoading = mutableStateOf(false)
    var shouldDialogSuccessfulRegister = mutableStateOf(false)

    fun registerAccount() {
        safeLet(
            email.value,
            password.value,
            name.value,
            cpf.value,
            onResult = { email, password, name, cpf ->
                singUpValidator.validateCredentialsSingUp(
                    email = email,
                    password = password,
                    name = name,
                    cpf = cpf,
                    onValidate = {
                        it.data?.let { validator ->
                            validator.dontHasError?.let { result ->
                                if (result) {
                                    messageErrorNameValidator.value =
                                        validator.dataMessages?.last()?.second.toString()

                                    validator.dataMessages?.last()?.third?.let {
                                        isErrorName.value = it
                                    }

                                    messageErrorEmailValidator.value = validator.dataMessages?.first()?.second.toString()

                                    validator.dataMessages?.first()?.third?.let {
                                        isErrorEmail.value = it
                                    }
                                    messageErrorCpfValidator.value =
                                        validator.dataMessages?.get(1)?.second.toString()

                                    validator.dataMessages?.get(1)?.third?.let {
                                        isErrorCpf.value = it
                                    }

                                    messageErrorPasswordValidator.value =
                                        validator.dataMessages?.get(2)?.second.toString()

                                    validator.dataMessages?.get(2)?.third?.let {
                                        isErrorPassword.value = it
                                    }
                                } else {
                                    CoroutineScope(Dispatchers.IO).launch {
                                        singUpRepository.registerAccount(
                                            email = email,
                                            password = password,
                                            name = name,
                                            cpf = cpf,
                                            onSuccessData = { _ ->
                                                shouldDialogSuccessfulRegister.value = true
                                            },
                                            onFailureError = { _ ->
                                                showErrorFeedBack.value = true
                                            },
                                            onShowLoading = {
                                                shouldLoading.value = it
                                            }
                                        )
                                    }
                                }
                            }
                        }
                    })

            },
            onFailure = {
                showErrorFeedBackEmptyCredentials.value = true
            })

    }

    fun backToNavigate() {
        viewModelScope.launch {
            routerNavigation?.navigatePop(RouterNavigationEnum.LOGIN)
        }
    }

}