package com.farma.poc.core.utils

import android.text.Spannable
import android.text.SpannableString
import android.text.Spanned
import android.text.style.StrikethroughSpan
import java.text.NumberFormat
import java.util.*


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

fun safeLet(
    param1: String? = null,
    param2: String? = null,
    param3: String? = null,
    param4: String? = null,
    onResult: ((String, String, String, String) -> Unit)? = null,
    onFailure: (() -> Unit)? = null
) {
    if (!param1.isNullOrEmpty() && !param2.isNullOrEmpty() && !param3.isNullOrEmpty() && !param4.isNullOrEmpty()) {
        onResult?.invoke(param1, param2,param3,param4)
    } else {
        onFailure?.invoke()
    }
}

fun varArgLet(
    vararg params: Boolean,
    onResult: (params: BooleanArray, length: Int) -> Unit,


) {
   onResult.invoke(params, params.size)
}


fun convertDoubleToMonetaryValue(value: Double): String {
    val locale = Locale("pt", "BR")
    return NumberFormat.getCurrencyInstance(locale).format(value)
}

fun setSpannableString(value: String): String {
    val spannable = SpannableString(value)
    spannable.setSpan(StrikethroughSpan(),0,value.length, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
    return spannable.toString()
}