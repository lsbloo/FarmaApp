package com.farma.poc.core.config.constants

import androidx.compose.runtime.mutableStateOf
import com.farma.poc.BuildConfig

object ConfigApplicationConstants {

    // DATA STORE NAME
    const val DATA_STORE_NAME = "farmaappsettings"

    // DATABASE_NAME
    const val NAME_DATABASE = "farma_db.db"
    const val VERSION_DATABASE = 35


    internal object Shared {
        const val SHARED_TIME_SESSION = "SHARED_TIME_SESSION"
        const val SHARED_TOKEN_SESSION = "SHARED_TOKEN_SESSION"
        const val SHARED_ACRONYM_USER_SESSION = "SHARED_ACRONYM_USER_SESSION"
        const val SHARED_BIOMETRIC_USER = "SHARED_BIOMETRIC_USER"
        const val SHARED_EMAIL_USER = "SHARED_EMAIL_USER"
        const val SHARED_CLIENT_TOKEN_USER = "CLIENT_TOKEN"
    }

    internal object PREFERENCES_SECURITY {
        const val KEY_NAME_BIOMETRIC = "FARMA_APP_KEY_NAME"
        const val KEY_STORE_BIOMETRIC = "FARMA_APP_SD1203"
        var AUTHENTICATE_WITH_BIOMETRIC = mutableStateOf(false)
        const val AUTHENTICATE_BIOMETRIC_WITH_CIPHER = false
    }

}