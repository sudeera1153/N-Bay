package com.takg.nbay.ui.components

import androidx.lifecycle.ViewModel
import com.takg.nbay.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DrawerViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
    fun doSignOut() {
        authRepository.signOut()
    }
}