package com.farma.poc.core.config.data.provider

import android.content.Context
import com.farma.poc.core.config.constants.ConfigApplicationConstants.DataProviderActionsRedirect.REDIRECT_ACTION_SETTINGS_TO_ADDRESS
import com.farma.poc.core.store.DataStoreConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.launch

class DataProvider(private val context: Context) : OnDataProvider {

    var config: DataStoreConfig? = null
    private val keyList = listOf<String>(REDIRECT_ACTION_SETTINGS_TO_ADDRESS)


    override fun initializeProvider() {
        config = DataStoreConfig(context = context)
    }


    override fun setOnActionDataProviderRedirect(data: OnDataProviderSettingsNavigation) {
        keyList.find { it == data.key }?.let { keyValid ->
            when (keyValid) {
                REDIRECT_ACTION_SETTINGS_TO_ADDRESS -> {
                    CoroutineScope(Dispatchers.IO).launch {
                        config?.setActionRedirectSettingsToAddressActivateRequestGetAllAddress(data.value as Boolean)
                    }
                }
                else -> {}
            }
        }

    }

    override fun getResultOnActionDataProvider(
        data: OnDataProviderSettingsNavigation,
        onResult: (Any) -> Unit
    ) {
        keyList.find { it == data.key }?.let { keyValid ->
            when (keyValid) {
                REDIRECT_ACTION_SETTINGS_TO_ADDRESS -> {
                    CoroutineScope(Dispatchers.IO).launch {
                        config?.redirectActionSettingsToAddressFlow?.take(1)?.collect { value ->
                            onResult.invoke(value)
                        }
                    }
                }
                else -> {}
            }
        }
    }

    override fun getKeys() = keyList


    override fun getKeyListSize() = keyList.size

}