package com.farma.poc.core.base

import com.farma.poc.core.config.network.ResultTask

interface BaseNetworkTask<T,R> {
    suspend fun call(
        t: T? = null,
        callback: (ResultTask.OnSuccess<T>?, ResultTask.OnFailure<R>?, onShouldLoading: (Boolean)?) -> Unit
    )
}

abstract class BaseNetworkTaskImpl<T,R> : BaseNetworkTask<T,R> {}