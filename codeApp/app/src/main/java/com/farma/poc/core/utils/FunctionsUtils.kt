package com.farma.poc.core.utils


fun safeLet(
    param1: String? = null,
    param2: String? = null,
    onResult: ((String, String) -> Unit)? = null,
    onFailure: (() -> Unit)? = null
) {
    if (!param1.isNullOrEmpty() && !param2.isNullOrEmpty()) {
        onResult?.invoke(param1, param2)
    } else {
        onFailure?.invoke()
    }
}