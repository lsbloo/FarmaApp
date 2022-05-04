package com.farma.poc.features.login.validators

import com.farma.poc.core.base.PojoValidators
import com.farma.poc.core.utils.typeValidator.PojoValidator
import com.farma.poc.core.utils.varArgLet
import com.farma.poc.features.login.validators.interfaces.LoginValidator

class LoginValidatorImpl<T> : PojoValidators<T>(), LoginValidator<T> {


    private fun validateIsEmail(email: String): Boolean {
        val matcher = android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
        return !matcher
    }

    private fun validateLenghtMinPassword(password: String): Boolean {
        return password.length <= 5
    }

    private fun getMessageErrorEmail() = "Preencha o campo de email, corretamente!"

    private fun getMessageErrorMinPassword() = "Password deve ter mais de 6 digitos"

    private fun setupMessagesError(index: Int, notHasError: Boolean): String {
        val dataMessages = listOf<String>(
            getMessageErrorEmail(),
            getMessageErrorMinPassword(),

            )
        return if (notHasError) dataMessages[index] else ""
    }

    override fun validateCredentialsLogin(
        email: String,
        password: String,
        onSuccess: ((OnSuccessValidator<PojoValidator>) -> Unit)?,
        onFailure: ((OnFailureValidator<PojoValidator>) -> Unit)?,
        onValidate: (OnValidator<PojoValidator>) -> Unit
    ) {
        varArgLet(
            validateIsEmail(email = email),
            validateLenghtMinPassword(password = password),
            onResult = { params, _ ->
                onValidate.invoke(
                    doValidate(
                        PojoValidator(
                            dataMessages = listOf(
                                Triple(0, setupMessagesError(0, params[0]), params[0]),
                                Triple(1, setupMessagesError(1, params[1]), params[1]),
                            ),
                            dontHasError = params[0] || params[1]
                        )
                    )
                )

            })
    }

}