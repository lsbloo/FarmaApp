package com.farma.poc.features.singup.presentation

import android.content.Context
import com.farma.poc.core.base.BaseViewModel
import com.farma.poc.core.navigation.RouterNavigationEnum
import com.farma.poc.features.singup.data.repository.SingUpRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SingUpViewModel(private val singUpRepository: SingUpRepository,context: Context) : BaseViewModel(context) {



    fun backToNavigate() {
            routerNavigation?.navigatePop(RouterNavigationEnum.LOGIN)
    }

}