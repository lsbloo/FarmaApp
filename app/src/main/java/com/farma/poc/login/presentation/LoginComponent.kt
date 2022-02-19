package com.farma.poc.login.presentation


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.farma.poc.R
import com.farma.poc.core.resources.colors.Colors

@Composable
fun screenLogin(){
    Scaffold(modifier = Modifier.fillMaxSize()) {
        topBanner()
    }
}



@Composable
@Preview(showBackground = true)
fun topBanner(){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf<Color>(
                        Colors.gradientLawrenciumOne,
                        Colors.gradientLawrenciumThree,
                        Colors.gradientLawrenciumThree
                    ),
                ), alpha = 1.2f
            )
    ) {
        bodyContent()
    }

}

@Composable
fun bodyContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(80.dp))
        Image(
            painter = painterResource(id = R.drawable.logo_farm), contentDescription = "",
            modifier = Modifier
                .width(180.dp)
                .height(180.dp)
                .align(Alignment.CenterHorizontally)
        )
    }
}

