package com.farma.poc.featuresApp.compose.onboarding.data.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "onboarding")
data class OnboardingDTO(
    @PrimaryKey(autoGenerate = false)
    val id: Int? = 1,
    val onboardingScreen: List<ItensOboardingDTO>? = null,
) : Parcelable
