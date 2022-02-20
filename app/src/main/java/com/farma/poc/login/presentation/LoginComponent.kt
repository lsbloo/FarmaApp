package com.farma.poc.login.presentation


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.farma.poc.R
import com.farma.poc.core.resources.colors.Colors
import com.farma.poc.core.utils.components.customOutlinedTextField

@ExperimentalUnitApi
@Composable
fun screenLogin(){
    Scaffold(modifier = Modifier.fillMaxSize()) {
        topBanner()
    }
}


@ExperimentalUnitApi
@Composable
@Preview(showBackground = true)
fun topBanner(){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf<Color>(
                        Colors.gradientSky,
                        Colors.gradientSkyTwo,
                        Colors.gradientSky
                    ),
                ), alpha = 1.2f
            )
    ) {
        bodyContent()
    }

}

@ExperimentalUnitApi
@Composable
fun bodyContent() {
    var loginText by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.height(80.dp))
        Image(
            painter = painterResource(id = R.drawable.logo_farm), contentDescription = "",
            modifier = Modifier
                .width(180.dp)
                .height(180.dp)
                .align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(40.dp))
        customOutlinedTextField(modifier = Modifier
            .padding(start = 20.dp, end = 20.dp)
            .fillMaxWidth(),
            isPassword = true,
            loginText,
            { newValue -> loginText = newValue },
            label =
            {
                Text(
                    text = "username", style = TextStyle(
                        color = Color.Black,
                        fontWeight = FontWeight.Normal,
                        letterSpacing = TextUnit(4F, TextUnitType(10L))
                    )
                )
            },
            placeholder = { Text(text = "Insira sua senha") })
    }
}

