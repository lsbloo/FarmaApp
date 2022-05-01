package com.farma.poc.features.singup.validators.interfaces

import com.farma.poc.core.base.BaseValidator
import com.farma.poc.core.utils.typeValidator.PojoValidator

interface SingUpValidator<T> {

    fun validateCredentialsSingUp(
        email: String,
        name: String,
        cpf: String,
        password: String,
        onSuccess: ((BaseValidator.OnSuccessValidator<PojoValidator>) -> Unit) ? = null,
        onFailure: ((BaseValidator.OnFailureValidator<PojoValidator>) -> Unit) ? = null,
        onValidate: (BaseValidator.OnValidator<PojoValidator>) -> Unit
    )
}