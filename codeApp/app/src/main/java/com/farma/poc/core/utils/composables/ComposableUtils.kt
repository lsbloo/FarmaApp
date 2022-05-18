package com.farma.poc.core.utils.composables

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import com.farma.poc.core.utils.components.CustomErrorFeedBack
import com.farma.poc.core.utils.enums.DurationSnackBarEnum
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.CoroutineScope

class ComposableUtils {

    companion object {
        @Composable
        fun setSystemUiControllerWithColorStatusBarAndDarkIcon(color: Color, darkIcons: Boolean) {
            val systemUiController = rememberSystemUiController()
            SideEffect {
                systemUiController.setStatusBarColor(
                    color = color,
                    darkIcons = darkIcons
                )
            }
        }

        @Composable
        fun setSystemUiControllerWithColorStatusBar(color: Color) {
            val systemUiController = rememberSystemUiController()
            SideEffect {
                systemUiController.setStatusBarColor(
                    color = color
                )
            }
        }

        @Composable
        fun showOrHideStatusBar(visibility: Boolean) {
            val systemUiController = rememberSystemUiController()
            SideEffect {
                systemUiController.isStatusBarVisible = visibility
            }
        }

        @Composable
        fun showOrHideSystemBar(visibility: Boolean) {
            val systemUiController = rememberSystemUiController()
            SideEffect {
                systemUiController.isSystemBarsVisible = visibility
            }
        }

        @Composable
        fun setBackHandler(enable: Boolean, onClickBackPressed: (() -> Unit)? = null) {
            BackHandler(enabled = enable) {
                onClickBackPressed?.invoke()
            }
        }


        @Composable
        fun showSnackBarError(
            scaffoldState: SnackbarHostState,
            coroutineScope: CoroutineScope,
            enable: Boolean,
            message: String,
            actionLabel: String,
            onApply: (() -> Unit)? = null,
            onActionPerformed: (() -> Unit)? = null,
            durationSnackBarEnum: DurationSnackBarEnum? = null
        ) {
            if (enable) {
                CustomErrorFeedBack(
                    snackBarHostState = scaffoldState,
                    coroutineScope = coroutineScope,
                    message = message,
                    actionLabel = actionLabel,
                    durationSnackBar = durationSnackBarEnum ?: DurationSnackBarEnum.SHORT,
                    actionPerformed = {
                        onActionPerformed?.invoke()
                    }
                ).apply {
                    setupSnackBarError()
                    onApply?.invoke()
                }
            }
        }
    }
}