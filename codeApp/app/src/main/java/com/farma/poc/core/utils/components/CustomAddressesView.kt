package com.farma.poc.core.utils.components

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.farma.poc.R
import com.farma.poc.core.resources.colors.Colors
import com.farma.poc.core.resources.fonts.FontsTheme
import com.farma.poc.featuresApp.compose.address.data.models.AddressDTO

class CustomAddressesView(
    val address: AddressDTO,
    val index: Int = 0,
    val onClickAddress: ((AddressDTO) -> Unit)? = null,
    var onClickDeleteAddress: ((AddressDTO) -> Unit)? = null
) {

    @Composable
    @ExperimentalUnitApi
    fun setup() {
        Card(
            modifier = Modifier
                .height(100.dp)
                .padding(start = 8.dp, end = 8.dp, top = 16.dp, bottom = 16.dp),
            elevation = 0.dp,
            shape = RoundedCornerShape(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Colors.blackBackGroundPrimaryTheme)
            ) {

                Column {
                    Spacer(modifier = Modifier.height(12.dp))
                    Image(
                        painter = painterResource(id = R.drawable.ic_house),
                        contentDescription = "",
                        modifier = Modifier
                            .width(30.dp)
                            .height(30.dp)
                            .padding(top = 12.dp)

                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(280.dp)
                        .background(color = Colors.blackBackGroundPrimaryTheme),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center
                ) {
                    Row(
                        modifier = Modifier.clickable {
                            onClickAddress?.invoke(address)
                        }
                    ) {
                        CustomTextView().apply {
                            address.street?.let { address ->
                                customTextView(
                                    text = address,
                                    upperCase = false,
                                    color = Colors.whitePrimary,
                                    modifier = Modifier.padding(1.dp),
                                    textStyle =
                                    FontsTheme(
                                        shadow = Shadow(
                                            color = Colors.whitePrimary,
                                        )
                                    ).typography.h3,
                                    fontSize = TextUnit(20F, TextUnitType(20))
                                )
                            }
                        }
                        Spacer(modifier = Modifier.width(1.dp))
                        Image(
                            painter = painterResource(id = R.drawable.ic_locate),
                            contentDescription = "",
                            modifier = Modifier
                                .width(16.dp)
                                .height(16.dp)
                        )
                    }

                    CustomTextView().apply {
                        address.number?.let { number ->
                            customTextView(
                                text = number,
                                upperCase = false,
                                color = Colors.whitePrimary,
                                modifier = Modifier.padding(1.dp),
                                textStyle =
                                FontsTheme(
                                    shadow = Shadow(
                                        color = Colors.whitePrimary,
                                    )
                                ).typography.h4,
                                fontSize = TextUnit(20F, TextUnitType(20))
                            )
                        }
                    }
                    CustomTextView().apply {
                        address.city?.let { city ->
                            customTextView(
                                text = city,
                                upperCase = false,
                                color = Colors.whitePrimary,
                                modifier = Modifier.padding(1.dp),
                                textStyle =
                                FontsTheme(
                                    shadow = Shadow(
                                        color = Colors.whitePrimary,
                                    )
                                ).typography.h4,
                                fontSize = TextUnit(20F, TextUnitType(20))
                            )
                        }
                    }
                }
                Column {
                    Spacer(modifier = Modifier.height(12.dp))
                    Image(
                        painter = painterResource(id = R.drawable.ic_trash),
                        contentDescription = "",
                        modifier = Modifier
                            .width(24.dp)
                            .height(24.dp)
                            .clickable {
                                onClickDeleteAddress?.invoke(address)
                            }
                    )

                }

            }
        }
    }
}