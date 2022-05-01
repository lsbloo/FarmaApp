package com.farma.poc.core.utils.components

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.*
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.farma.poc.core.resources.colors.Colors
import com.farma.poc.core.resources.fonts.FontsTheme
import com.farma.poc.core.utils.colors.OutlinedTextFieldColor.Companion.getDefaultTextFieldOutlinedColor


class ErrorModalBottomSheet(
    private val action: () -> Unit,
    private val showOrHideBottomSheet: Boolean,
    private val messageFeedBack: String,
) {

    @ExperimentalUnitApi
    @ExperimentalAnimationApi
    @ExperimentalMaterialApi
    @Composable
    fun errorModalBottomSheet(): CustomModalBottomSheet {
        return CustomModalBottomSheet(
            color = Colors.black,
            sheetContent = {
                Card(
                    modifier = Modifier
                        .height(100.dp)
                        .fillMaxWidth(), elevation = 4.dp, backgroundColor = Color.Black
                ) {
                    Row(modifier = Modifier.fillMaxSize()) {
                        customTextField(
                            modifier = Modifier
                                .fillMaxSize()
                                .align(Alignment.CenterVertically),
                            isPassword = false,
                            state = messageFeedBack,
                            textStyle = FontsTheme(
                                shadow = Shadow(
                                    Colors.whitePrimary,
                                    blurRadius = 2F,
                                ),
                                color = Colors.whitePrimary,
                                fontSize = 18.sp
                            ).typography.h2,
                            onValueChange = { _ ->
                            }
                        , colorsTextField = getDefaultTextFieldOutlinedColor())
                    }
                }
            }
        ).apply {
            if (showOrHideBottomSheet) {
                this.customModalBottomSheet(ModalBottomSheetValue.Expanded, showOrHideBottomSheet)
            } else {

                this.customModalBottomSheet(ModalBottomSheetValue.Hidden,showOrHideBottomSheet)
            }
            action.invoke()

        }

    }
}