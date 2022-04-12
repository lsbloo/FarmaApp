package com.farma.poc.features.onboarding.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ItensOboardingDTO(
    val image: String? = null,
    val title: String? = null,
    val description: String? = null,
) : Parcelable