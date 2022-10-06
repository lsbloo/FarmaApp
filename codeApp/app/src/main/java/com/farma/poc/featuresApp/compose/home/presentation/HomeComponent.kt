package com.farma.poc.featuresApp.compose.home.presentation

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
import com.farma.poc.R
import com.farma.poc.core.base.BaseActivity
import com.farma.poc.core.resources.colors.Colors
import com.farma.poc.core.resources.fonts.FontsTheme
import com.farma.poc.core.utils.colors.OutlinedTextFieldColor
import com.farma.poc.core.utils.components.*
import com.farma.poc.core.utils.components.CustomAlertDialog.Companion.setupDialogLogout
import com.farma.poc.core.utils.components.CustomBottomNavigation.Companion.setupBottomNavigationHome
import com.farma.poc.core.utils.components.CustomCircularImageView.Companion.setupCategories
import com.farma.poc.core.utils.components.CustomHighLightProductView.Companion.setHighLightProductView
import com.farma.poc.core.utils.components.CustomProductView.Companion.setupProductView
import com.farma.poc.core.utils.composables.ComposableUtils


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@ExperimentalUnitApi
@Composable
fun homeComponent(activity: BaseActivity, homeViewModel: HomeViewModel) {
    homeViewModel.getItemsHome()
    val scaffoldState = rememberScaffoldState()

    ComposableUtils.setSystemUiControllerWithColorStatusBar(
        color = Colors.redQuin
    )

    Scaffold(
        topBar = {
            CustomTopBar(
                backGroundColor = Colors.redQuin,
                imageSupportText = R.drawable.logo_farma,
                textTopBar = activity.getString(R.string.app_name),
                imageCart = R.drawable.ic_cart,
                labelSearchTextField = activity.getString(R.string.label_field_search),
                iconLabelSearchField = R.drawable.ic_search,
                hasSearchField = true,
                colorsTextField = OutlinedTextFieldColor.getDefaultTextFieldOutlinedColor()
            ).apply {
                setupTopBar()
            }
        },
        scaffoldState = scaffoldState,
        modifier = Modifier.fillMaxSize(), content = {
            DefaultSnackBar(
                snackbarHostState = scaffoldState.snackbarHostState,
                modifier = Modifier,
                onDismiss = {
                    scaffoldState.snackbarHostState.currentSnackbarData?.dismiss()
                }
            ).apply {
                defaultSnackBar()
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf<Color>(
                                Colors.colorBackGroundPrimaryTheme,
                                Colors.colorBackGroundPrimaryTheme
                            ),
                        )
                    )
            ) {
                bodyContent(homeViewModel, activity, scaffoldState = scaffoldState)
            }
        })

    if (homeViewModel.logoutAppEvent.value) {
        setupDialogLogout(
            title = activity.getString(R.string.label_title_dialog_logout),
            labelConfirmButton = activity.getString(R.string.label_confirm_button_dialog_logout),
            labelDismissButton = activity.getString(R.string.label_dismiss_button_dialog_logoutt),
            textDescription = activity.getString(R.string.label_description_dialog_logout),
            onClickDismissButton = {
                homeViewModel.dismissDialogLogout.value = false
                homeViewModel.logoutAppEvent.value = false
            },
            onClickConfirmButton = {
                activity.finish()
            }, onDismiss = {
                false
            }
        )
    }

    ComposableUtils.setBackHandler(
        enable = true,
        onClickBackPressed = {
            homeViewModel.logoutAppEvent.value = true
            homeViewModel.dismissDialogLogout.value = true
        }
    )
}

@ExperimentalUnitApi
@Composable
fun bodyContent(homeViewModel: HomeViewModel, context: Context, scaffoldState: ScaffoldState) {
    Column(modifier = Modifier
        .fillMaxSize(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = object : Arrangement.Vertical {
            var currentOffset = 0
            override fun Density.arrange(totalSize: Int, sizes: IntArray, outPositions: IntArray) {
                sizes.forEachIndexed { index, size ->
                    if (index == sizes.lastIndex) {
                        outPositions[index] = totalSize - (size)
                    } else {
                        outPositions[index] = currentOffset
                        currentOffset += size
                    }
                }
            }

        }) {
        Spacer(modifier = Modifier.height(12.dp))
        CustomTextView().customTextView(
            text = context.getString(R.string.highlight),
            modifier =
            Modifier
                .align(Alignment.Start)
                .padding(start = 12.dp),
            color = Colors.whitePrimary,
            textStyle = FontsTheme(
                shadow = Shadow(
                    color = Colors.whitePrimary,
                    blurRadius = 2F,
                )
            ).typography.h2
        )
        Spacer(modifier = Modifier.height(6.dp))
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
        ) {
            itemsIndexed(homeViewModel.getHighLights()) { index, item ->
                Box(modifier = Modifier.padding(start = 12.dp, end = 12.dp)) {
                    setHighLightProductView(
                        productDTO = item,
                        index = index,
                        image = if (homeViewModel.imagesHighLights.size != 0) {
                            homeViewModel.getCurrentHighLightImage(item.name)
                        } else null,
                        onCLickProduct = { _, _ ->
                            Log.d("CLICK_DESTAQUE", "DESTAQUE CLICADO")
                            Toast.makeText(
                                context,
                                R.string.function_not_implemented,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        CustomTextView().customTextView(
            text = context.getString(R.string.categories),
            modifier =
            Modifier
                .align(Alignment.Start)
                .padding(start = 12.dp),
            color = Colors.whitePrimary,
            textStyle = FontsTheme(
                shadow = Shadow(
                    color = Colors.whitePrimary,
                    blurRadius = 2F,
                )
            ).typography.h2
        )
        Spacer(modifier = Modifier.height(6.dp))
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
        ) {
            itemsIndexed(homeViewModel.getItemsCategories()) { index, item ->
                Box(modifier = Modifier.padding(start = 12.dp, end = 12.dp)) {
                    setupCategories(
                        textImageView = item.name,
                        image = if (homeViewModel.imagesCategories.size != 0) {
                            homeViewModel.getCurrentCategorieImage(item.name)
                        } else null,
                        onClickImage = {
                            Toast.makeText(
                                context,
                                R.string.function_not_implemented,
                                Toast.LENGTH_SHORT
                            ).show()
                        }, index = index
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        CustomTextView().customTextView(
            text = context.getString(R.string.product_highlight),
            modifier =
            Modifier
                .align(Alignment.Start)
                .padding(start = 12.dp),
            color = Colors.whitePrimary,
            textStyle = FontsTheme(
                shadow = Shadow(
                    color = Colors.whitePrimary,
                    blurRadius = 2F,
                )
            ).typography.h2
        )
        Spacer(modifier = Modifier.height(6.dp))
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .height(260.dp)
        ) {
            itemsIndexed(homeViewModel.getItemsProductHighLight()) { index, item ->
                Box(modifier = Modifier.padding(start = 12.dp, end = 12.dp)) {
                    setupProductView(item,
                        index,
                        image = if (homeViewModel.imagesProductsHighLights.size != 0) {
                            homeViewModel.getCurrentProductHighLightImage(item.name)
                        } else null,
                        onCLickProduct = { productId, Index ->
                            Log.d("ProductClick", "" + productId)
                            Toast.makeText(
                                context,
                                R.string.function_not_implemented,
                                Toast.LENGTH_SHORT
                            ).show()
                        })
                }
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        Box(
            Modifier
                .fillMaxWidth()
                .height(50.dp)
                .horizontalScroll(
                    state = rememberScrollState()
                )
                .background(color = Colors.blackSecundary)
        ) {

            setupBottomNavigationHome(
                context = context,
                onClickStart = {
                    Toast.makeText(context, "Inicio Clicado", Toast.LENGTH_SHORT).show()
                },
                onClickHelp = {
                    Toast.makeText(context, "Ajuda Clicado", Toast.LENGTH_SHORT).show()
                },
                onCLickMore = {
                    homeViewModel.redirectToSettings()
                })
        }
    }
}
