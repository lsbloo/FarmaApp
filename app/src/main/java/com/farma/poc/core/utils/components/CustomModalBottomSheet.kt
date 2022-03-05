package com.farma.poc.core.utils.components

import android.os.Handler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

class CustomModalBottomSheet @ExperimentalMaterialApi constructor(
    private val color: Color? = null,
    private val sheetContent: @Composable () -> Unit,
) {

    @ExperimentalAnimationApi
    @ExperimentalMaterialApi
    @Composable
    fun customModalBottomSheet(state: ModalBottomSheetValue, visibily: Boolean) {
        var stateBottomSheet: BottomSheetValue? = null
        stateBottomSheet = if(state == ModalBottomSheetValue.Expanded){
            BottomSheetValue.Expanded
        } else {
            BottomSheetValue.Collapsed
        }

        return ModalBottomSheetLayout(
            sheetElevation = 12.dp,
            sheetBackgroundColor = Color.Black,
            sheetState = ModalBottomSheetState(initialValue = state),
            modifier = Modifier.defaultMinSize(minHeight = 1.dp, minWidth = 1.dp),
            sheetContent = {
                AnimatedVisibility(visible = visibily) {
                    Box(modifier = Modifier.height(100.dp).swipeable(
                        state = BottomSheetState(
                            initialValue =  stateBottomSheet,
                            ),
                        anchors = mapOf(
                            Pair(0f,stateBottomSheet)
                        ),
                        thresholds = {_,_ -> FractionalThreshold(0.89f) },
                        orientation = Orientation.Vertical
                    )) {
                        sheetContent.invoke()
                    }
                }
            }) {

        }
    }
}

@ExperimentalMaterialApi
@Stable
class BottomSheetState(
    initialValue: BottomSheetValue,
    animationSpec: AnimationSpec<Float> = SwipeableDefaults.AnimationSpec,
    confirmStateChange: (BottomSheetValue) -> Boolean = { true }
) : SwipeableState<BottomSheetValue>(
    initialValue = initialValue,
    animationSpec = animationSpec,
    confirmStateChange = confirmStateChange
)