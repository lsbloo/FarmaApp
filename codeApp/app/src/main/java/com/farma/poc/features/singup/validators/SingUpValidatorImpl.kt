package com.farma.poc.features.singup.validators

import android.text.TextUtils
import com.farma.poc.core.base.PojoValidators
import com.farma.poc.core.utils.typeValidator.PojoValidator
import com.farma.poc.core.utils.varArgLet
import com.farma.poc.features.singup.validators.interfaces.SingUpValidator

class SingUpValidatorImpl<T> : PojoValidators<PojoValidator>(), SingUpValidator<T> {


    private fun validateLenghtName(name: String): Boolean {
        return name.length <= 10
    }

    private fun validateIsEmail(email: String): Boolean {
        val matcher = android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
        return !matcher
    }

    private fun validateLenghtMinPassword(password: String): Boolean {
        return password.length <= 5
    }

    private fun validateLenghtCpf(cpf: String): Boolean {
        return cpf.length < 11
    }

    private fun getMessageErrorEmail() = "Preencha o campo de email, corretamente!"

    private fun getMessageErrorLengthCpf() = "Tamanho do CPF incompativel"

    private fun getMessageErrorMinPassword() = "Password deve ter mais de 6 digitos"

    private fun getMessageErrorName() = "Nome deve ter mais de 10 digitos."

    private fun getParam(boolean: Boolean) = boolean


    private fun setupMessagesError(index: Int, notHasError: Boolean): String {
        val dataMessages = listOf<String>(
            getMessageErrorEmail(),
            getMessageErrorLengthCpf(),
            getMessageErrorMinPassword(),
            getMessageErrorName()
        )
        return if (notHasError) dataMessages[index] else ""
    }

    override fun validateCredentialsSingUp(
        email: String,
        name: String,
        cpf: String,
        password: String,
        onSuccess: ((OnSuccessValidator<PojoValidator>) -> Unit)?,
        onFailure: ((OnFailureValidator<PojoValidator>) -> Unit)?,
        onValidate: (OnValidator<PojoValidator>) -> Unit
    ) {
        varArgLet(
            validateIsEmail(email = email),
            validateLenghtCpf(cpf = cpf),
            validateLenghtMinPassword(password = password),
            validateLenghtName(name = name),
            onResult = { params, _ ->
                onValidate.invoke(
                    doValidate(
                        PojoValidator(
                            dataMessages = listOf(
                                Triple(0, setupMessagesError(0, params[0]), params[0]),
                                Triple(1, setupMessagesError(1, params[1]), params[1]),
                                Triple(2, setupMessagesError(2, params[2]), params[2]),
                                Triple(3, setupMessagesError(3, params[3]), params[3]),
                            ),
                            dontHasError = params[0] || params[1] || params[2] || params[3]
                        )
                    )
                )

            })
    }
}