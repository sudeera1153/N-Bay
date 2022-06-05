package com.takg.nbay.ui.screens.user_profile

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.takg.nbay.common.Resource
import com.takg.nbay.domain.use_case.GetCurrentUser
import com.takg.nbay.ui.screens.listings.index.ListingsViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class UserProfileViewModel @Inject constructor(
    private val getCurrentUser: GetCurrentUser
) : ViewModel() {
    var displayName = mutableStateOf("")

    init {
        loadCurrentUser()
    }

    private fun loadCurrentUser() {
        getCurrentUser().onEach { result ->
            when (result) {
                is Resource.Error -> displayName.value = "N/A"
                is Resource.Loading -> displayName.value = "Loading..."
                is Resource.Success -> displayName.value = result.data!!.name.orEmpty()
            }
        }.launchIn(viewModelScope)
    }
}