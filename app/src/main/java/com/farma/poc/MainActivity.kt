package com.farma.poc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.farma.poc.core.navigation.RouterNavigation
import com.farma.poc.login.presentation.screenLogin
import com.farma.poc.ui.theme.FarmaAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FarmaAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = RouterNavigation.LOGIN.name){
                        composable(route = RouterNavigation.LOGIN.name) { screenLogin() }
                    }
                }
            }
        }
    }
}