package com.farma.poc.login.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.farma.poc.core.navigation.RouterNavigation
import com.farma.poc.core.navigation.RouterNavigationEnum
import com.farma.poc.login.data.models.ResponseLoginDTO
import com.farma.poc.login.data.repository.LoginRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {

    private val _authenticateUser = MutableLiveData<ResponseLoginDTO?>()
    var authenticateLogin: LiveData<ResponseLoginDTO?> = _authenticateUser
    private var routerNavigation: RouterNavigation? = null

    private var showLoadingLogin = MutableLiveData<Boolean>(false)
    var statusShowLoading: LiveData<Boolean> = showLoadingLogin

    fun setNavigation(navController: RouterNavigation) {
        this.routerNavigation = navController
    }

    suspend fun login(){
        loginRepository.authenticateUser(
            onSuccess = {
                _authenticateUser.postValue(it)
                redirectHomeApp()
            },
            onFailure = {
                _authenticateUser.postValue(it)
            },
            onShowLoading = {
                showLoadingLogin.postValue(it)
            }
        )
    }

    private fun redirectHomeApp(){
        CoroutineScope(Dispatchers.Main).launch {
            routerNavigation?.navigateTo(RouterNavigationEnum.HOME)
        }
    }
}