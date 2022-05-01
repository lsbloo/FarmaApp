package com.farma.poc.core.base

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Build
import androidx.annotation.RequiresApi
import com.farma.poc.core.config.network.ResultTask

interface BaseNetworkTask<E,T,R> {
    suspend fun call(
        e: E? = null,
        callback: (ResultTask.OnSuccess<T>?, ResultTask.OnFailure<R>?, onShouldLoading: (Boolean)?) -> Unit,
        errorNetWorkNotAvailable: () -> Unit,
    )
}

abstract class BaseNetworkTaskImpl<E,T,R> (private val context: Context): BaseNetworkTask<E,T,R> {

    var hasNetworkAvailable: Boolean = false
    init {
        hasNetworkAvailable = verifyIfHasNetworkAvailable()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun getNetWorkInfo(): NetworkInfo? {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        connectivityManager?.let { connectivityManager ->
            val infoNetwork = connectivityManager.activeNetworkInfo
            return infoNetwork
        } ?: run {
            return null
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun verifyIfHasNetworkAvailable(): Boolean {
        getNetWorkInfo()?.let {
            return it.isConnected && it.isAvailable
        } ?: run {
            return false
        }
    }
}