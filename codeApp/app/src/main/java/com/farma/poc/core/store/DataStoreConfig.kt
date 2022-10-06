package com.farma.poc.core.store

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.farma.poc.core.config.constants.ConfigApplicationConstants
import com.farma.poc.featuresApp.compose.address.data.models.AddressDTO
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch


val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = ConfigApplicationConstants.DATA_STORE_NAME)


class DataStoreConfig(private val context: Context) {

    private val sharedAddressDetail =
        stringPreferencesKey(ConfigApplicationConstants.Shared.SHARED_ADDRESS_DETAIL)

    private val sharedAddressDetailSelected =
        stringPreferencesKey(ConfigApplicationConstants.Shared.SHARED_ADDRESS_DETAIL_PRINCIPAL)

    private val sharedTimeSession =
        stringPreferencesKey(ConfigApplicationConstants.Shared.SHARED_TIME_SESSION)

    private val sharedClientToken =
        stringPreferencesKey(ConfigApplicationConstants.Shared.SHARED_CLIENT_TOKEN_USER)

    private val sharedTokenSession =
        stringPreferencesKey(ConfigApplicationConstants.Shared.SHARED_TOKEN_SESSION)

    private val sharedAcronymnUser =
        stringPreferencesKey(ConfigApplicationConstants.Shared.SHARED_ACRONYM_USER_SESSION)

    private val sharedEmailUser =
        stringPreferencesKey(ConfigApplicationConstants.Shared.SHARED_EMAIL_USER)

    private val sharedShowBiometric =
        booleanPreferencesKey(ConfigApplicationConstants.Shared.SHARED_BIOMETRIC_USER)

    val sharedClientTokenFlow: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[sharedClientToken] ?: ""
    }

    private val redirectActionSettingsToAddress =
        booleanPreferencesKey(ConfigApplicationConstants.DataProviderActionsRedirect.REDIRECT_ACTION_SETTINGS_TO_ADDRESS)


    val redirectActionSettingsToAddressFlow: Flow<Boolean> =
        context.dataStore.data.map { preferences ->
            preferences[redirectActionSettingsToAddress] ?: ""
        } as Flow<Boolean>

    val sharedAddressDetailFlow: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[sharedAddressDetail] ?: ""
    }

    val sharedAddressDetailPrincipalFlow: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[sharedAddressDetailSelected] ?: ""
    }

    val sharedTimeSessionFlow: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[sharedTimeSession] ?: ""
    }

    val sharedTokenSessionFlow: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[sharedTokenSession] ?: ""
    }

    val sharedAcronymnUserFlow: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[sharedAcronymnUser] ?: ""
    }

    val sharedFlagShowBiometric: Flow<Boolean> = context.dataStore.data.map { preferences ->
        preferences[sharedShowBiometric] ?: false
    }

    val sharedEmailuser: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[sharedEmailUser] ?: ""
    }


    suspend fun setFlagShowBiometric(flagShow: Boolean) {
        context.dataStore.edit { settings ->
            settings[sharedShowBiometric] = flagShow
        }
    }

    suspend fun setAcronymUserFlow(sharedAcronymUser: String) {
        context.dataStore.edit { settings ->
            settings[sharedAcronymnUser] = sharedAcronymUser
        }
    }

    suspend fun setTypeToken(sharedSession: String) {
        context.dataStore.edit { settings ->
            settings[sharedTimeSession] = sharedSession
        }
    }

    suspend fun setClientIdToken(dataToken: String?) {
        dataToken?.let {
            context.dataStore.edit { settings ->
                settings[sharedClientToken] = it
            }
        }

    }

    suspend fun setSharedTokenSession(sharedTokenSessionx: String) {
        context.dataStore.edit { settings ->
            settings[sharedTokenSession] = sharedTokenSessionx
        }
    }

    suspend fun setSharedEmailUser(email: String) {
        context.dataStore.edit { settings ->
            settings[sharedEmailUser] = email
        }
    }

    suspend fun setAddressDetailTmp(address: String) {
        context.dataStore.edit { settings ->
            settings[sharedAddressDetail] = address
        }
    }

    suspend fun setAddressDetailSelected(address: String) {
        context.dataStore.edit { settings ->
            settings[sharedAddressDetail] = address
        }
    }


    suspend fun setActionRedirectSettingsToAddressActivateRequestGetAllAddress(value: Boolean) {
        context.dataStore.edit { settings ->
            settings[redirectActionSettingsToAddress] = value
        }
    }

}