package com.takg.nbay.ui.screens.auth

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.AuthCredential
import com.takg.nbay.common.Resource
import com.takg.nbay.domain.repository.AuthRepository
import javax.inject.Inject
import com.takg.nbay.common.Resource.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repo: AuthRepository,
    val oneTapClient: SignInClient
) : ViewModel() {
    val isUserAuthenticated get() = repo.isUserAuthenticatedInFirebase()



    private val _oneTapSignInState = mutableStateOf<Resource<BeginSignInResult>>(Loading())
    val oneTapSignInState: State<Resource<BeginSignInResult>> = _oneTapSignInState

    private val _oneTapSignUpState = mutableStateOf<Resource<BeginSignInResult>>(Loading())
    val oneTapSignUpState: State<Resource<BeginSignInResult>> = _oneTapSignUpState

    private val _signInState = mutableStateOf<Resource<Boolean>>(Loading())
    val signInState: State<Resource<Boolean>> = _signInState

    private val _createUserState = mutableStateOf<Resource<Boolean>>(Loading())
    val createUserState: State<Resource<Boolean>> = _createUserState

    fun oneTapSignIn() {
        viewModelScope.launch {
            repo.oneTapSignInWithGoogle().collect { response ->
                _oneTapSignInState.value = response
            }
        }
    }

    fun oneTapSignUp() {
        viewModelScope.launch {
            repo.oneTapSignUpWithGoogle().collect { response ->
                _oneTapSignUpState.value = response
            }
        }
    }

    fun signInWithGoogle(googleCredential: AuthCredential) {
        viewModelScope.launch {
            repo.firebaseSignInWithGoogle(googleCredential).collect { response ->
                _signInState.value = response
            }
        }
    }

    fun createUser() {
        viewModelScope.launch {
            repo.createUserInFirestore().collect { response ->
                _createUserState.value = response
            }
        }
    }
}