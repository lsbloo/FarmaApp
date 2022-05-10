package com.farma.poc.core.store

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.farma.poc.core.config.constants.ConfigApplicationConstants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = ConfigApplicationConstants.DATA_STORE_NAME)


class DataStoreConfig(private val context: Context) {

    private val sharedTimeSession =
        stringPreferencesKey(ConfigApplicationConstants.Shared.SHARED_TIME_SESSION)

    private val sharedTokenSession =
        stringPreferencesKey(ConfigApplicationConstants.Shared.SHARED_TOKEN_SESSION)

    private val sharedAcronymnUser =
        stringPreferencesKey(ConfigApplicationConstants.Shared.SHARED_ACRONYM_USER_SESSION)

    private val sharedEmailUser = stringPreferencesKey(ConfigApplicationConstants.Shared.SHARED_EMAIL_USER)

    private val sharedShowBiometric =
        booleanPreferencesKey(ConfigApplicationConstants.Shared.SHARED_BIOMETRIC_USER)

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

    suspend fun setSharedTimeSession(sharedSession: String) {
        context.dataStore.edit { settings ->
            settings[sharedTimeSession] = sharedSession
        }
    }

    suspend fun setSharedTokenSession(sharedTokenSessionx: String) {
        context.dataStore.edit { settings ->
            settings[sharedTokenSession] = sharedTokenSessionx
        }
    }

    suspend fun setSharedEmailUser(email: String) {
        context.dataStore.edit {  settings ->
            settings[sharedEmailUser] = email
        }
    }
}