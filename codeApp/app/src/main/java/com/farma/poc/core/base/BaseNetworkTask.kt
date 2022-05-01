package com.farma.poc.core.base

import com.farma.poc.core.config.network.ResultTask

interface BaseNetworkTask<E,T,R> {
    suspend fun call(
        e: E? = null,
        callback: (ResultTask.OnSuccess<T>?, ResultTask.OnFailure<R>?, onShouldLoading: (Boolean)?) -> Unit
    )
}

abstract class BaseNetworkTaskImpl<E,T,R> : BaseNetworkTask<E,T,R> {}