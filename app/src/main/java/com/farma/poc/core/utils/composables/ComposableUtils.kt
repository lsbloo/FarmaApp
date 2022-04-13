package com.farma.poc.core.utils.composables

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

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
        fun setBackHandler(enable: Boolean, onClickBackPressed: (() -> Unit)? = null){
            BackHandler(enabled = enable) {
                onClickBackPressed?.invoke()
            }
        }
    }
}