package com.farma.poc.featuresApp.compose.onboarding.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ItensOboardingDTO(
    var image: String? = null,
    val title: String? = null,
    val description: String? = null,
    val labelButton: String? = null,
) : Parcelable