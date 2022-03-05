package com.farma.poc.core.utils.components

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.farma.poc.core.utils.enums.DurationSnackBarEnum
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class CustomErrorFeedBack(
    private val snackBarHostState: SnackbarHostState,
    private val message: String,
    private val actionLabel: String,
    private val durationSnackBar: DurationSnackBarEnum,
    private val coroutineScope: CoroutineScope,
    private val actionPerformed: (() -> Unit)? = null,
    private val dismissed: (() -> Unit)? = null,
) {

    @SuppressLint("CoroutineCreationDuringComposition")
    @Composable
    fun setupSnackBarError() {
        coroutineScope.launch {
            val snackBarResult = snackBarHostState.showSnackbar(
                message = message,
                actionLabel = actionLabel,
                duration = if (durationSnackBar.name == DurationSnackBarEnum.SHORT.name) {
                    SnackbarDuration.Short
                } else {
                    SnackbarDuration.Long
                }
            )
            when (snackBarResult) {
                SnackbarResult.Dismissed -> {
                    Log.d("SnackBarError", "Dismissed")
                    dismissed?.invoke()
                }
                SnackbarResult.ActionPerformed -> {
                    Log.d("SnackBarError", "ActionPerformed")
                    actionPerformed?.invoke()
                }
            }

        }
    }

    companion object {
        var instance: CustomErrorFeedBack? = null
        fun customErrorFeedBack(
            snackBarHostState: SnackbarHostState,
            message: String,
            actionLabel: String,
            durationSnackBar: DurationSnackBarEnum,
            coroutineScope: CoroutineScope,
            action: (() -> Unit)? = null,
        ): CustomErrorFeedBack {
            if (instance == null) {
                return CustomErrorFeedBack(
                    snackBarHostState = snackBarHostState,
                    coroutineScope = coroutineScope,
                    message = message,
                    actionLabel = actionLabel,
                    durationSnackBar = durationSnackBar,
                    actionPerformed = action
                )
            }
            return instance as CustomErrorFeedBack
        }
    }
}