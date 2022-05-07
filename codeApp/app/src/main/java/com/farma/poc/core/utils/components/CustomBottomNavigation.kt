package com.farma.poc.core.utils.components

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
import com.farma.poc.R
import com.farma.poc.core.resources.colors.Colors
import com.farma.poc.core.resources.fonts.FontsTheme

class CustomBottomNavigation(
    private val buttonsNavigation: @Composable () -> Unit,
    private val context: Context
) {

    @Composable
    fun setup() {
        buttonsNavigation.invoke()
    }

    companion object {
        @ExperimentalUnitApi
        @Composable
        fun setupBottomNavigationHome(
            context: Context,
            onClickStart: (() -> Unit)? = null,
            onClickHelp: (() -> Unit)? = null,
            onCLickMore: (() -> Unit)? = null
        ) {
            val localConfigurationCompose = LocalConfiguration.current

            val screenWidth = localConfigurationCompose.screenWidthDp
            val screenHeight = localConfigurationCompose.screenWidthDp
            val spaceSecondShortcut = screenWidth / 4
            val spaceThirdShortCut = (spaceSecondShortcut) + 12
            CustomBottomNavigation(
                buttonsNavigation = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 12.dp, end = 18.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Spacer(modifier = Modifier.width(8.dp))
                        ButtonNavigation(
                            labelButton = context.getString(R.string.label_bottom_navigation_start),
                            iconButton = R.drawable.ic_house,
                            onClickButton = { onClickStart?.invoke() }
                        ).setup()
                        Spacer(modifier = Modifier.width(spaceSecondShortcut.dp))
                        ButtonNavigation(
                            labelButton = context.getString(R.string.label_bottom_navigation_help),
                            iconButton = R.drawable.ic_help,
                            onClickButton = { onClickHelp?.invoke() }
                        ).setup()
                        Spacer(modifier = Modifier.width(spaceThirdShortCut.dp))
                        ButtonNavigation(
                            labelButton = context.getString(R.string.label_bottom_navigation_more),
                            iconButton = R.drawable.ic_more,
                            onClickButton = { onCLickMore?.invoke() }).setup()
                    }
                }, context = context
            ).setup()
        }
    }
}

class ButtonNavigation(
    private val labelButton: String,
    private val onClickButton: (() -> Unit)? = null,
    private val iconButton: Int
) {

    @ExperimentalUnitApi
    @Composable
    fun setup() {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 4.dp, end = 4.dp),
        ) {
            Column(modifier = Modifier
                .fillMaxSize()
                .clickable {
                    onClickButton?.invoke()
                }) {
                Icon(
                    painter = painterResource(id = iconButton),
                    contentDescription = "",
                    modifier = Modifier
                        .padding(start = 12.dp, top = 4.dp)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = labelButton,
                    color = Colors.black,
                    textAlign = TextAlign.Left,
                    style = FontsTheme(
                        shadow = Shadow(
                            color = Colors.black,
                            blurRadius = 2F,
                        )
                    ).typography.h3
                )
            }
        }
    }
}