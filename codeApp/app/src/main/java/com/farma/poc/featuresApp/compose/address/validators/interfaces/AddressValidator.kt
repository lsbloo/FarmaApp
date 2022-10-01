package com.farma.poc.featuresApp.compose.address.validators.interfaces

import com.farma.poc.core.base.BaseValidator
import com.farma.poc.core.utils.typeValidator.PojoValidator

interface AddressValidator<T> {

    fun validateCredentialsAddress(
        street: String,
        district: String,
        number: String,
        city: String,
        description: String,
        onSuccess: ((BaseValidator.OnSuccessValidator<PojoValidator>) -> Unit)? = null,
        onFailure: ((BaseValidator.OnFailureValidator<PojoValidator>) -> Unit)? = null,
        onValidate: (BaseValidator.OnValidator<PojoValidator>) -> Unit
    )
}