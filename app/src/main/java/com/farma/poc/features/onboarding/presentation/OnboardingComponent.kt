package com.farma.poc.features.onboarding.presentation

import android.content.Context
import android.os.Handler
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import timber.log.Timber


@Composable
fun setupOnboardingScreen(onboardingViewModel: OnboardingViewModel, context: Context){


    onboardingViewModel.onboardingDataSet?.let {
        Column(modifier = Modifier.fillMaxSize()) {
            it.onboardingScreen?.first()?.title?.let { it1 -> Text(text = it1) }
            Box(modifier = Modifier.height(8.dp))
            it.onboardingScreen?.first()?.description?.let { it1 -> Text(text = it1) }
            Spacer(modifier = Modifier.height(8.dp))
            it.onboardingScreen?.first()?.image?.let { it1 -> Text(text = it1) }
        }
    }



    BackHandler(enabled = true) {
        Log.d("Click Back", "handler ok")
    }
}