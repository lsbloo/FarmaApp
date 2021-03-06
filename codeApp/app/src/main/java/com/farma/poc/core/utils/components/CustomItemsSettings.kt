package com.farma.poc.core.utils.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.farma.poc.R
import com.farma.poc.core.resources.colors.Colors
import com.farma.poc.core.resources.fonts.FontsTheme
import com.farma.poc.features.settings.home.data.model.GetSettingsDTO

class CustomItemsSettings(
    private val itemsSettings: @Composable () -> Unit
) {

    @Composable
    fun setup() {
        itemsSettings.invoke()
    }

    companion object {
        @ExperimentalUnitApi
        @Composable
        fun setupItemsSettingsScreen(
            data: GetSettingsDTO, onCLickOrder: (() -> Unit)? = null,
            onCLickInfoUser: (() -> Unit)? = null,
            onClickAsks: (() -> Unit)? = null,
            onCLickMethodPayment: (() -> Unit)? = null,
            onClickAddress: (() -> Unit)? = null,
            onClickCloseAccount: (() -> Unit)? = null,
            onCheckedValueChangeBiometric: ((Boolean) -> Unit)? = null,
            initStateSwitchToggleBiometric: Boolean = false
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ItemSettings(data.labelOrder, onClickButton = {
                    onCLickOrder?.invoke()
                }).setup()
                Spacer(modifier = Modifier.height(12.dp))
                ItemSettings(data.labelInfoUser, onClickButton = {
                    onCLickInfoUser?.invoke()
                }).setup()
                Spacer(modifier = Modifier.height(12.dp))
                ItemSettings(data.labelAsks, onClickButton = {
                    onClickAsks?.invoke()
                }).setup()
                Spacer(modifier = Modifier.height(12.dp))
                ItemSettings(data.labelMethodPayment, onClickButton = {
                    onCLickMethodPayment?.invoke()
                }).setup()
                Spacer(modifier = Modifier.height(12.dp))
                ItemSettings(data.labelAddress, onClickButton = {
                    onClickAddress?.invoke()
                })
                Spacer(modifier = Modifier.height(12.dp))
                ItemSettings(
                    data.labelBiometric,
                    hasSwitchToggle = true,
                    initStateSwitchToggle = initStateSwitchToggleBiometric,
                    onCheckedValueChange = {
                        onCheckedValueChangeBiometric?.invoke(it)
                    }).setup()
                Spacer(modifier = Modifier.height(12.dp))
                ItemSettings(data.labelCloseAccount, onClickButton = {
                    onClickCloseAccount?.invoke()
                }).setup()
            }
        }
    }
}


class ItemSettings(
    private val label: String?,
    private val onClickButton: (() -> Unit)? = null,
    private val hasSwitchToggle: Boolean = false,
    private val onCheckedValueChange: ((Boolean) -> Unit)? = null,
    private val initStateSwitchToggle: Boolean = false
) {

    @ExperimentalUnitApi
    @Composable
    fun setup() {

        val modifierItemSettings = if (onClickButton != null) {
            Modifier
                .fillMaxSize()
                .clickable {
                    onClickButton?.invoke()
                }
        } else {
            Modifier
                .fillMaxSize()
        }
        val stateCheckedSwitch = remember { mutableStateOf(initStateSwitchToggle) }
        Column(modifier = Modifier.fillMaxSize()) {
            Row(
                modifier = modifierItemSettings, horizontalArrangement = Arrangement.SpaceBetween
            ) {
                if (label != null) {
                    Text(
                        modifier = Modifier.width(180.dp),
                        text = label,
                        color = Colors.whitePrimary,
                        textAlign = TextAlign.Left,
                        style = FontsTheme(
                            shadow = Shadow(
                                color = Colors.whitePrimary,
                                blurRadius = 2F,
                            )
                        ).typography.h3
                    )
                }
                if (hasSwitchToggle) {
                    Switch(
                        checked = stateCheckedSwitch.value,
                        onCheckedChange = {
                            stateCheckedSwitch.value = it
                            onCheckedValueChange?.invoke(stateCheckedSwitch.value)
                        })
                } else {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow_right),
                        contentDescription = ""
                    )
                }
            }
            Divider(
                color = Colors.dimGray, thickness = 1.dp, modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 1.dp, top = 8.dp)
            )
        }
    }
}