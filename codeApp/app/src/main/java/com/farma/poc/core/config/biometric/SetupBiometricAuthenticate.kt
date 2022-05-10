package com.farma.poc.core.config.biometric

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.hardware.biometrics.BiometricManager.Authenticators.BIOMETRIC_STRONG
import android.os.Build
import android.provider.Settings
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.view.View
import androidx.annotation.RequiresApi
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.farma.poc.core.base.BaseActivity
import com.farma.poc.core.config.constants.ConfigApplicationConstants
import com.farma.poc.core.config.constants.ConfigApplicationConstants.PREFERENCES_SECURITY.AUTHENTICATE_BIOMETRIC_WITH_CIPHER
import java.security.KeyStore
import java.security.Provider
import java.security.Security
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey


class SetupBiometricInfo(private val activity: BaseActivity) {
    fun getAvailableBiometricInDevice(
        onBiometricSuccess: (() -> Unit)? = null,
        onBiometricNotHasHardware: (() -> Unit)? = null,
        onBiometricErrorHardware: (() -> Unit)? = null,
        onBiometricCreateCredentials: (() -> Unit)? = null,
        onBiometricErrorUnsupported: (() -> Unit)? = null,
    ) {
        val biometricManager = BiometricManager.from(activity.applicationContext)
        val managerPackage = activity.packageManager
        val hasFingerPrint = managerPackage.hasSystemFeature(PackageManager.FEATURE_FINGERPRINT)
        if (hasFingerPrint) {
            when (biometricManager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_WEAK or BiometricManager.Authenticators.DEVICE_CREDENTIAL)) {
                BiometricManager.BIOMETRIC_SUCCESS ->
                    onBiometricSuccess?.invoke()
                BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE ->
                    onBiometricNotHasHardware?.invoke()
                BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE ->
                    onBiometricErrorHardware?.invoke()
                BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> {
                    onBiometricCreateCredentials?.invoke()
                }
                BiometricManager.BIOMETRIC_ERROR_UNSUPPORTED -> {
                    onBiometricErrorUnsupported?.invoke()
                }
            }
        }
    }

    fun createCredentialsBiometrics() {
        val enrollIntent = Intent(Settings.ACTION_BIOMETRIC_ENROLL).apply {
            putExtra(
                Settings.EXTRA_BIOMETRIC_AUTHENTICATORS_ALLOWED,
                BIOMETRIC_STRONG or BiometricManager.Authenticators.DEVICE_CREDENTIAL
            )
        }
    }

    private fun setupBiometricDialog(title: String, subTitle: String): BiometricPrompt.PromptInfo {
        return BiometricPrompt.PromptInfo.Builder()
            .setTitle(title)
            .setSubtitle(subTitle)
            .setAllowedAuthenticators(BiometricManager.Authenticators.BIOMETRIC_WEAK or BiometricManager.Authenticators.DEVICE_CREDENTIAL)
            .build()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun setup(title: String, subTitle: String, listener: OnAuthenticationBiometric) {
        addProvider(ConfigApplicationConstants.PREFERENCES_SECURITY.KEY_STORE_BIOMETRIC, 1.0)

        val biometricPrompt = BiometricPrompt(activity as FragmentActivity, ContextCompat.getMainExecutor(activity.applicationContext), object : BiometricPrompt.AuthenticationCallback() {

                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                    listener.onAuthenticateError(error = errString)
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    listener.onAuthenticateFailed()
                }

                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    listener.onAuthenticateSuccess(result)
                }

            })

        if(AUTHENTICATE_BIOMETRIC_WITH_CIPHER) {
            val secretKey = KeyGenParameterSpec.Builder(
                ConfigApplicationConstants.PREFERENCES_SECURITY.KEY_NAME_BIOMETRIC,
                KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
            )
                .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
                .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7)
                .setUserAuthenticationRequired(false)
                .setInvalidatedByBiometricEnrollment(true)
                .build()

            generateSecretKey(
                secretKey,
                ConfigApplicationConstants.PREFERENCES_SECURITY.KEY_STORE_BIOMETRIC
            )

            val cipher = getCipher()

            val secretkey = getSecretKey(
                ConfigApplicationConstants.PREFERENCES_SECURITY.KEY_NAME_BIOMETRIC,
                ConfigApplicationConstants.PREFERENCES_SECURITY.KEY_STORE_BIOMETRIC
            )
            cipher.init(Cipher.ENCRYPT_MODE, secretkey)
            biometricPrompt.authenticate(
                setupBiometricDialog(title = title, subTitle = subTitle),
                BiometricPrompt.CryptoObject(cipher)
            )
        } else {
            biometricPrompt.authenticate(
                setupBiometricDialog(title = title, subTitle = subTitle)
            )
        }
    }

    fun encriptInfoCredentials(result: BiometricPrompt.AuthenticationResult): ByteArray? {
        return result.cryptoObject?.cipher?.doFinal()
    }


    private fun addProvider(keyStore: String, version: Double) {
        Security.addProvider(BiometricProvider(keyStore, version,"Provider biometric"))
    }

    private fun generateSecretKey(keyGanParameter: KeyGenParameterSpec, keyStore: String) {
        val keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES,keyStore)
        keyGenerator.init(keyGanParameter)
        keyGenerator.generateKey()
    }

    private fun getSecretKey(keyName: String, keyStore: String): SecretKey {
        val keyStore = KeyStore.getInstance(keyStore)
        keyStore.load(null)
        return keyStore.getKey(keyName, null) as SecretKey
    }

    private fun getCipher(): Cipher {
        return Cipher.getInstance(
            KeyProperties.KEY_ALGORITHM_AES + "/"
                    + KeyProperties.BLOCK_MODE_CBC + "/"
                    + KeyProperties.ENCRYPTION_PADDING_PKCS7
        )
    }

    companion object {
        fun getBiometric(activity: BaseActivity) = SetupBiometricInfo(activity = activity)
    }
}

class BiometricProvider(name: String, version: Double, info: String) : Provider (name,version,info) {

}
interface OnAuthenticationBiometric {
    fun onAuthenticateError(error: CharSequence)
    fun onAuthenticateFailed()
    fun onAuthenticateSuccess(result: BiometricPrompt.AuthenticationResult)
}