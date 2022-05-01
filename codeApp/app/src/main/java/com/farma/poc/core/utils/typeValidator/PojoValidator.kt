package com.farma.poc.core.utils.typeValidator

data class PojoValidator(
    var message: String? = null,
    var dontHasError: Boolean? = null,
    var dataMessages: List<Triple<Int, String, Boolean>>? = null,
)