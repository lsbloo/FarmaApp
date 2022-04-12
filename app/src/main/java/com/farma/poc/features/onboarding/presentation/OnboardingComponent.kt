package com.farma.poc.features.onboarding.presentation

import android.content.Context
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import timber.log.Timber


@Composable
fun setupOnboardingScreen(onboardingViewModel: OnboardingViewModel, context: Context){

    Text(text = "Hello Onboarding")

    BackHandler(enabled = true) {
        Log.d("Click Back", "handler ok")
    }
}