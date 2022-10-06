package com.farma.poc.core.config.data.provider

import kotlinx.coroutines.flow.Flow

interface OnDataProvider {
    fun initializeProvider()
    fun setOnActionDataProviderRedirect(data: OnDataProviderSettingsNavigation)
    fun getResultOnActionDataProvider(data: OnDataProviderSettingsNavigation, onResult: (Any) -> Unit)
    fun getKeys(): List<String>
    fun getKeyListSize(): Int
}

data class OnDataProviderSettingsNavigation(
     val key: String,
     val value: Any? = null,
){}
