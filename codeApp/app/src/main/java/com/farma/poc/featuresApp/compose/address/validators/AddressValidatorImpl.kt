package com.farma.poc.featuresApp.compose.address.validators

import com.farma.poc.core.base.PojoValidators
import com.farma.poc.core.utils.typeValidator.PojoValidator
import com.farma.poc.core.utils.varArgLet
import com.farma.poc.featuresApp.compose.address.validators.interfaces.AddressValidator

class AddressValidatorImpl<T> : PojoValidators<T>(), AddressValidator<T> {


    private fun validateIfValueIsNotNull(value: String) = value.isNullOrBlank()


    private val messageErrorStreet: String
        get() = "Nome da Rua não pode ser vazio!"

    private val messageErrorDistrict: String
        get() = "Bairro não pode ser vazio!"

    private val messageErrorNumber: String
        get() = "Número não pode ser vazio!"

    private val messageErrorCity: String
        get() = "Cidade não pode ser vazio!"


    private fun setupMessagesError(index: Int, notHasError: Boolean): String {
        val dataMessages = listOf<String>(
            messageErrorStreet,
            messageErrorDistrict,
            messageErrorNumber,
            messageErrorCity
        )
        return if (notHasError) dataMessages[index] else ""
    }

    override fun validateCredentialsAddress(
        street: String,
        district: String,
        number: String,
        city: String,
        description: String,
        onSuccess: ((OnSuccessValidator<PojoValidator>) -> Unit)?,
        onFailure: ((OnFailureValidator<PojoValidator>) -> Unit)?,
        onValidate: (OnValidator<PojoValidator>) -> Unit
    ) {
        varArgLet(validateIfValueIsNotNull(street),
            validateIfValueIsNotNull(district),
            validateIfValueIsNotNull(number),
            validateIfValueIsNotNull(city),
            onResult = { params, _ ->
                onValidate.invoke(
                    doValidate(
                        data = PojoValidator(
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