package com.farma.poc.core.config.constants

object ConfigApplicationConstants {

    // BASE URL API GATEWAY
    const val BASE_URL_MOCK = "https://private-0e35c-farmaapp.apiary-mock.com/"

    // DATA STORE NAME
    const val DATA_STORE_NAME = "farmaappsettings"

    // DATABASE_NAME
    const val NAME_DATABASE = "farma_db.db"
    const val VERSION_DATABASE = 16


    internal object Shared {
        const val SHARED_TIME_SESSION = "SHARED_TIME_SESSION"
        const val SHARED_TOKEN_SESSION = "SHARED_TOKEN_SESSION"
        const val SHARED_ACRONYM_USER_SESSION = "SHARED_ACRONYM_USER_SESSION"
    }

}