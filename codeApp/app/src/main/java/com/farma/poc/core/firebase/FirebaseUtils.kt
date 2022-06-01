package com.farma.poc.core.firebase

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.Composable
import com.farma.poc.BuildConfig
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.app
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.File
import java.time.LocalDate

private val storageRef: StorageReference by lazy {
    FirebaseStorage.getInstance(Firebase.app, BuildConfig.URL_FIREBASE_STORAGE).reference
}

fun downloadFileImageFirebase(
    pathString: String,
    onFailure: () -> Unit,
    onSuccess: (File) -> Unit
) {
    val ref = storageRef.child("images/onboarding_screen_1.png")
    val file = File.createTempFile("images", "jpg")
    ref.getFile(file).addOnSuccessListener {
        onSuccess.invoke(file)
    }.addOnFailureListener {
        onFailure.invoke()
    }
}

fun downloadUriImageFirebase(
    pathString: String? = null,
    onFailure: () -> Unit,
    onSuccess: (Uri) -> Unit
) {
    var path = ""
    path = if(pathString.isNullOrEmpty()) {
        "images/logo_farm.png"
    } else {
        pathString
    }

    val ref = storageRef.child(path)
    ref.downloadUrl.addOnSuccessListener {
        onSuccess.invoke(it)
    }.addOnFailureListener {
        onFailure.invoke()
    }
}