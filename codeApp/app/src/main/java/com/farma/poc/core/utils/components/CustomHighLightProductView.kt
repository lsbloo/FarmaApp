package com.farma.poc.core.utils.components

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.farma.poc.R
import com.farma.poc.core.resources.colors.Colors
import com.farma.poc.core.utils.dto.ProductDTO

class CustomHighLightProductView(
    private val productDTO: ProductDTO,
    val index: Int = 0,
    val image: Uri? = null,
    private val onCLickProduct: ((Long, Int) -> Unit)? = null
) {
    @ExperimentalUnitApi
    @Composable
    fun setup() {
        Box(
            modifier = Modifier
                .height(205.dp)
                .width(182.dp)
                .background(color = Colors.whitePrimary, shape = RoundedCornerShape(4.dp))
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                if(image == null) {
                    Image(
                        painter = painterResource(id = R.drawable.logo_farma),
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .width(52.dp)
                            .height(80.dp)
                            .shadow(elevation = 8.dp, shape = RoundedCornerShape(4.dp))
                            .clickable {
                                onCLickProduct?.invoke(0, index)
                            }
                    )
                }else {
                    Image(
                        painter = rememberAsyncImagePainter(image),
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                            .shadow(elevation = 8.dp, shape = RoundedCornerShape(4.dp))
                            .clickable {
                                onCLickProduct?.invoke(0, index)
                            }
                    )
                }
                Spacer(modifier = Modifier.height(2.dp))
            }
        }
    }


    companion object {
        @Composable
        @ExperimentalUnitApi
        fun setHighLightProductView(
            productDTO: ProductDTO,
            index: Int = 0,
            image: Uri? = null,
            onCLickProduct: ((Long, Int) -> Unit)? = null
        ) = CustomHighLightProductView(
            productDTO = productDTO,
            index = index,
            image = image,
            onCLickProduct = onCLickProduct
        ).setup()
    }
}