package com.farma.poc.core.config.network

import java.lang.Exception

sealed class ResultTask<out R> {
    data class OnSuccess<out T>(val data: T?) : ResultTask<T?>()
    data class OnFailure<out T>(val data: T?) : ResultTask<T?>()
    data class OnError(val exception: Exception): ResultTask<Nothing>()

}