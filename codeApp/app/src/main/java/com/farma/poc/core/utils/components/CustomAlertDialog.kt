package com.farma.poc.core.utils.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
import com.farma.poc.core.resources.colors.Colors
import com.farma.poc.core.resources.fonts.FontsTheme

class CustomAlertDialog(
    private val title: String,
    private var labelConfirmButton: String,
    private var labelDismissButton: String,
    private var textDescription: String,
    private var onClickConfirmButton: () -> Unit,
    private var onClickDismissButton: () -> Unit,
    private var onDismiss: () -> Unit
) {


    companion object {
        @ExperimentalUnitApi
        @Composable
        fun setupDialogLogout(
            title: String,
            labelConfirmButton: String,
            labelDismissButton: String,
            textDescription: String,
            onClickConfirmButton: () -> Unit,
            onClickDismissButton: () -> Unit,
            onDismiss: () -> Unit
        ) = CustomAlertDialog(
            title = title,
            labelConfirmButton = labelConfirmButton,
            labelDismissButton = labelDismissButton,
            textDescription = textDescription,
            onClickConfirmButton = onClickConfirmButton,
            onClickDismissButton = onClickDismissButton,
            onDismiss = onDismiss
        ).setupDialog()
    }

    @ExperimentalUnitApi
    @Composable
    fun setupDialog() {
        AlertDialog(
            onDismissRequest = onDismiss,
            title = {
                Text(
                    text = title,
                    color = Colors.black,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(start = 65.dp, end = 40.dp),
                    style = FontsTheme(
                        shadow = Shadow(
                            color = Colors.whitePrimary,
                            blurRadius = 2F,
                        )
                    ).typography.h2
                )
            },
            confirmButton = {
                Button(
                    onClick = { onClickConfirmButton.invoke() },
                    elevation = ButtonDefaults.elevation(
                        defaultElevation = 6.dp,
                        pressedElevation = 8.dp,
                        disabledElevation = 0.dp
                    ),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Colors.redSecundary),
                    contentPadding = ButtonDefaults.ContentPadding,
                    enabled = true,
                    shape = RoundedCornerShape(4.dp),
                ) {
                    Text(
                        text = labelConfirmButton,
                        color = Colors.whitePrimary,
                        textAlign = TextAlign.Center,
                        style = FontsTheme(
                            shadow = Shadow(
                                color = Colors.whitePrimary,
                                blurRadius = 2F,
                            )
                        ).typography.h3
                    )
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        onClickDismissButton.invoke()
                    },
                    elevation = ButtonDefaults.elevation(
                        defaultElevation = 6.dp,
                        pressedElevation = 8.dp,
                        disabledElevation = 0.dp
                    ),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Colors.bluePrimary),
                    contentPadding = ButtonDefaults.ContentPadding,
                    enabled = true,
                    shape = RoundedCornerShape(4.dp),
                ) {
                    Text(
                        text = labelDismissButton,
                        color = Colors.whitePrimary,
                        textAlign = TextAlign.Center,
                        style = FontsTheme(
                            shadow = Shadow(
                                color = Colors.whitePrimary,
                                blurRadius = 2F,
                            )
                        ).typography.h3
                    )
                }
            },
            text = {
                Text(
                    text = textDescription,
                    color = Colors.black,
                    textAlign = TextAlign.Center,
                    style = FontsTheme(
                        shadow = Shadow(
                            color = Colors.whitePrimary,
                            blurRadius = 2F,
                        )
                    ).typography.h3
                )
            },
            contentColor = Colors.colorBackGroundPrimaryTheme
        )
    }
}