package com.takg.nbay.ui.screens.auth.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.takg.nbay.common.Resource
import com.takg.nbay.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
    var email = mutableStateOf("")
    var password = mutableStateOf("")

    private val loginEventChannel = Channel<Resource<Unit>>()
    val loginEvents = loginEventChannel.receiveAsFlow()

    fun doLogin() {
        viewModelScope.launch {
            authRepository.signIn(email.value.trim(), password.value).collect { event ->
                loginEventChannel.send(event)
            }
        }
    }
}