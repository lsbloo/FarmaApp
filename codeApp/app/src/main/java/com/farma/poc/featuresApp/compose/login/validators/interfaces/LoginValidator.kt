package com.farma.poc.featuresApp.compose.login.validators.interfaces

import com.farma.poc.core.base.BaseValidator
import com.farma.poc.core.utils.typeValidator.PojoValidator

interface LoginValidator<T> {

    fun validateCredentialsLogin(
        email: String,
        password: String,
        onSuccess: ((BaseValidator.OnSuccessValidator<PojoValidator>) -> Unit)? = null,
        onFailure: ((BaseValidator.OnFailureValidator<PojoValidator>) -> Unit)? = null,
        onValidate: (BaseValidator.OnValidator<PojoValidator>) -> Unit
    )
}