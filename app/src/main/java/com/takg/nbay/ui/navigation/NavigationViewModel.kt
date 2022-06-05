package com.takg.nbay.ui.navigation

import androidx.lifecycle.ViewModel
import com.takg.nbay.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NavigationViewModel @Inject constructor(
    private val repo: AuthRepository
) : ViewModel() {
    val isUserAuthenticated get() = repo.isUserAuthenticatedInFirebase()
}