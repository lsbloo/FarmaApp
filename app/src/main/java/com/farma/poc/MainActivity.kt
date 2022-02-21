package com.farma.poc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.farma.poc.core.navigation.RouterNavigationEnum
import com.farma.poc.core.navigation.RouterNavigationManager
import com.farma.poc.home.presentation.homeComponent
import com.farma.poc.login.presentation.LoginViewModel
import com.farma.poc.login.presentation.screenLogin
import com.farma.poc.ui.theme.FarmaAppTheme
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MainActivity : ComponentActivity() {

    @ExperimentalUnitApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FarmaAppTheme {
                // A surface container using the 'background' color from the theme
                val loginViewModel = getViewModel<LoginViewModel>()
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = RouterNavigationEnum.LOGIN.name){
                        val routerNavigationManager = RouterNavigationManager(navController, this)
                        loginViewModel.setNavigation(routerNavigationManager)


                        composable(route = RouterNavigationEnum.LOGIN.name) { screenLogin(loginViewModel, this@MainActivity) }
                        composable(route = RouterNavigationEnum.HOME.name) { homeComponent() }
                    }
                }
            }
        }
    }
}