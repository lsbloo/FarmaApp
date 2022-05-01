package com.farma.poc.core.base

import com.farma.poc.core.utils.typeValidator.PojoValidator

sealed class BaseValidator<out R> {
    data class OnSuccessValidator<out T>(val data: T?) : BaseValidator<T?>()
    data class OnFailureValidator<out T>(val data: T?) : BaseValidator<T?>()
    data class OnValidator<out T>(val data: T?) : BaseValidator<T?>()
}

abstract class PojoValidators<T> : BaseValidator<T>() {

    fun doValidateSuccessful(data: PojoValidator): OnSuccessValidator<PojoValidator> {
        return OnSuccessValidator(data = data)
    }

    fun doValidateFailure(data: PojoValidator): OnFailureValidator<PojoValidator> {
        return OnFailureValidator(data = data)
    }

    fun doValidate(data: PojoValidator): OnValidator<PojoValidator> {
        return OnValidator(data = data)
    }
}
