package com.farma.poc.core.utils.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
import com.farma.poc.R
import com.farma.poc.core.resources.colors.Colors
import com.farma.poc.core.resources.fonts.FontsTheme
import com.farma.poc.core.utils.convertDoubleToMonetaryValue
import com.farma.poc.core.utils.dto.ProductDTO
import com.farma.poc.core.utils.setSpannableString

class CustomProductView(
    val productDTO: ProductDTO,
    val index: Int = 0,
    val onCLickProduct: ((Long, Int) -> Unit)? = null
) {

    @ExperimentalUnitApi
    @Composable
    fun setup() {
        Box(
            modifier = Modifier
                .height(220.dp)
                .width(160.dp)
                .background(color = Colors.whitePrimary, shape = RoundedCornerShape(4.dp))
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo_farma),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(48.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                productDTO.name?.let { productName ->
                    Text(
                        text = productName,
                        color = Colors.black,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(start = 8.dp),
                        style = FontsTheme(
                            fontWeight = FontWeight.SemiBold,
                            shadow = Shadow(
                                color = Colors.whitePrimary,
                                blurRadius = 2F,
                            )
                        ).typography.h4
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                productDTO.drug?.let { drugDTO ->
                    drugDTO.manufacturer?.let { manufacturer ->
                        Text(
                            text = manufacturer,
                            color = Colors.black,
                            textAlign = TextAlign.Left,
                            modifier = Modifier
                                .padding(start = 8.dp, end = 56.dp)
                                .width(80.dp),
                            style = FontsTheme(
                                fontWeight = FontWeight.W300,
                                shadow = Shadow(
                                    color = Colors.whitePrimary,
                                    blurRadius = 2F,
                                )
                            ).typography.h5
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    drugDTO.apresentation?.let { apresentation ->
                        Text(
                            text = apresentation,
                            color = Colors.black,
                            textAlign = TextAlign.Left,
                            modifier = Modifier
                                .padding(start = 8.dp, end = 56.dp)
                                .width(80.dp),
                            style = FontsTheme(
                                fontWeight = FontWeight.W300,
                                shadow = Shadow(
                                    color = Colors.whitePrimary,
                                    blurRadius = 2F,
                                )
                            ).typography.h5
                        )
                    }
                }
                Spacer(modifier = Modifier.height(12.dp))
                productDTO.maxValue?.let { maxValue ->
                    Text(
                        text = convertDoubleToMonetaryValue(maxValue),
                        color = Colors.black,
                        textAlign = TextAlign.Left,
                        modifier = Modifier
                            .padding(start = 8.dp, end = 56.dp)
                            .width(80.dp),
                        style = FontsTheme(
                            textDecoration = TextDecoration.LineThrough,
                            fontWeight = FontWeight.Light,
                            shadow = Shadow(
                                color = Colors.whitePrimary,
                                blurRadius = 2F,
                            )
                        ).typography.h4
                    )
                }
                Spacer(modifier = Modifier.height(2.dp))
                productDTO.minValue?.let { minValue ->
                    Text(
                        text = convertDoubleToMonetaryValue(minValue),
                        color = Colors.black,
                        textAlign = TextAlign.Left,
                        modifier = Modifier
                            .padding(start = 8.dp, end = 56.dp)
                            .width(80.dp),
                        style = FontsTheme(
                            fontWeight = FontWeight.SemiBold,
                            shadow = Shadow(
                                color = Colors.whitePrimary,
                                blurRadius = 2F,
                            )
                        ).typography.h4
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))
                CustomCircularButton().apply {
                    customCircularButton(
                        content = {
                            Text(
                                text = "Comprar",
                                color = Colors.whitePrimary,
                                textAlign = TextAlign.Center,
                                style = FontsTheme(
                                    shadow = Shadow(
                                        color = Colors.whitePrimary,
                                        blurRadius = 2F,
                                    )
                                ).typography.h4
                            )
                        },
                        onClick = {
                            productDTO.id?.let { onCLickProduct?.invoke(it, index) }
                        },
                        elevation = ButtonDefaults.elevation(
                            defaultElevation = 6.dp,
                            pressedElevation = 8.dp,
                            disabledElevation = 0.dp
                        ),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Colors.blueSecundary),
                        contentPadding = ButtonDefaults.ContentPadding,
                        enabled = true,
                        shape = RoundedCornerShape(4.dp),
                        modifier = Modifier
                            .width(100.dp)
                            .height(30.dp)

                    )
                }
            }

        }
    }


    companion object {
        @Composable
        @ExperimentalUnitApi
        fun setupProductView(
            productDTO: ProductDTO,
            index: Int = 0,
            onCLickProduct: ((Long, Int) -> Unit)? = null
        ) = CustomProductView(
            productDTO = productDTO,
            index = index,
            onCLickProduct = onCLickProduct
        ).setup()
    }
}