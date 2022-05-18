package com.farma.poc.features.onboarding.presentation

import android.content.Context
import android.os.Handler
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.annotation.FloatRange
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
import com.farma.poc.R
import com.farma.poc.core.resources.colors.Colors
import com.farma.poc.core.resources.fonts.FontsTheme
import com.farma.poc.core.utils.components.CustomCircularButton
import com.farma.poc.core.utils.components.CustomTextView
import com.farma.poc.core.utils.composables.ComposableUtils
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber


@ExperimentalPagerApi
@ExperimentalUnitApi
@Composable
fun setupOnboardingScreen(onboardingViewModel: OnboardingViewModel, context: Context) {

    ComposableUtils.setSystemUiControllerWithColorStatusBarAndDarkIcon(
        color = Colors.colorBackGroundPrimaryTheme,
        darkIcons = true
    )

    val pageState = rememberPageState(
        pageCount = 2,
        initialOffscreenLimit = 1,
        infiniteLoop = false,
        initialPage = 0
    )

    LaunchedEffect(key1 = pageState.currentPage) {
        launch {
            delay(2000)
            with(pageState) {
                if (onboardingViewModel.stateViewPageIndex != 2 && currentPage != 1) {
                    val target = if (currentPage < onboardingViewModel.stateViewPageIndex) currentPage + 1 else 0

                    animateScrollToPage(
                        page = target,
                        animationSpec = tween(
                            durationMillis = 200,
                            easing = FastOutSlowInEasing
                        )
                    )
                }
            }
        }
    }

    var labelButton: String? = null
    HorizontalPager(state = pageState) { index ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf<Color>(
                            Colors.colorBackGroundPrimaryTheme,
                            Colors.colorBackGroundPrimaryTheme
                        ),
                    ), alpha = 1.2f
                )
        ) {
            onboardingViewModel.onboardingDataSet?.let { onboardingDataScreen ->
                labelButton = onboardingDataScreen.onboardingScreen?.get(index)?.labelButton
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(
                            rememberScrollState()
                        ).padding(start = 12.dp, end = 12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = remember {
                        object : Arrangement.Vertical {
                            override fun Density.arrange(
                                totalSize: Int,
                                sizes: IntArray,
                                outPositions: IntArray
                            ) {
                                var currentOffeset = 0
                                sizes.forEachIndexed { index, size ->
                                    if (index == sizes.lastIndex) {
                                        outPositions[index] = totalSize - (size + 80)

                                    } else {
                                        outPositions[index] = currentOffeset
                                        currentOffeset += size
                                    }

                                }
                            }

                        }
                    }
                ) {
                    Spacer(modifier = Modifier.height(60.dp))
                    Image(
                        painter = painterResource(id = R.drawable.logo_farma),
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(244.dp)
                            .clip(CircleShape)
                            .background(color = Colors.blackBackGroundPrimaryTheme)
                    )
                    Spacer(modifier = Modifier.height(48.dp))
                    onboardingDataScreen.onboardingScreen?.get(index)?.title?.let {
                        CustomTextView().customTextView(
                            text = it,
                            modifier =
                            Modifier
                                .align(Alignment.CenterHorizontally),
                            color = Colors.whitePrimary,
                            textStyle = FontsTheme(
                                shadow = Shadow(
                                    color = Colors.whitePrimary,
                                    blurRadius = 2F,
                                )
                            ).typography.h2
                        )
                    }
                    Spacer(modifier = Modifier.height(48.dp))
                    onboardingDataScreen.onboardingScreen?.get(index)?.description?.let {
                        CustomTextView().customTextView(
                            text = it,
                            modifier =
                            Modifier
                                .align(Alignment.CenterHorizontally),
                            color = Colors.whitePrimary,
                            textStyle = FontsTheme(
                                shadow = Shadow(
                                    color = Colors.whitePrimary,
                                    blurRadius = 2F,
                                )
                            ).typography.h2
                        )
                    }
                    Spacer(modifier = Modifier.height(48.dp))
                    CustomCircularButton().apply {
                        customCircularButton(
                            content = {
                                CustomTextView().apply {
                                    labelButton?.let {
                                        customTextView(
                                            text = it,
                                            upperCase = false,
                                            modifier =
                                            Modifier.padding(12.dp),
                                            color = Colors.whitePrimary,
                                            textStyle =
                                            FontsTheme(
                                                shadow = Shadow(
                                                    color = Colors.whitePrimary,
                                                    blurRadius = 2F,
                                                )
                                            ).typography.h2
                                        )
                                    }
                                }
                            },
                            onClick = {
                                with(onboardingViewModel) {
                                    animateToPage(index + 1, redirectLogin = {
                                        redirectToLogin()
                                    })
                                }
                            },
                            elevation = ButtonDefaults.elevation(
                                defaultElevation = 6.dp,
                                pressedElevation = 8.dp,
                                disabledElevation = 0.dp
                            ),
                            colors = ButtonDefaults.buttonColors(backgroundColor = Colors.redSecundary),
                            contentPadding = ButtonDefaults.ContentPadding,
                            enabled = true,
                            shape = RoundedCornerShape(6.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(60.dp)
                                .padding(start = 32.dp, end = 32.dp)

                        )
                    }
                }
            }
        }
    }
    ComposableUtils.setBackHandler(
        enable = true,
        onClickBackPressed = {
            Log.d("OnPressed", "OnPressed Onboarding Called")
        }
    )
}

@ExperimentalPagerApi
@Composable
fun rememberPageState(
    pageCount: Int,
    initialPage: Int = 0,
    @FloatRange(from = 0.0, to = 1.0) initialPageOffset: Float = 0f,
    initialOffscreenLimit: Int = 1,
    infiniteLoop: Boolean = false
): PagerState = rememberSaveable(saver = PagerState.Saver) {
    PagerState(
        pageCount = pageCount,
        currentPage = initialPage,
        currentPageOffset = initialPageOffset,
        offscreenLimit = initialOffscreenLimit,
        infiniteLoop = infiniteLoop
    )
}