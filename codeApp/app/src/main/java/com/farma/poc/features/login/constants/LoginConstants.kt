package com.farma.poc.features.login.constants

class LoginConstants {


    class View {
        companion object {
            const val LABEL_EMAIL = "email"
            const val LABEL_PASSWORD = "password"
            const val LABEL_FORGOT_PASSWORD = "forgot password?"
            const val LABEL_OR_CONNECT_WITH = "or connect with"
            const val BUTTON_LABEL_LOGIN = "login"
            const val BUTTON_LABEL_FACEBOOK = "facebook"
            const val BUTTON_LABEL_GOOGLE = "google"
        }
    }

    class API {
        companion object {
            const val ENDPOINT_LOGIN = "/login"
        }
    }
}