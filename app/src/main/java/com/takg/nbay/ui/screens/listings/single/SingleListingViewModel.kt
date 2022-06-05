package com.takg.nbay.ui.screens.listings.single

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.takg.nbay.common.Resource
import com.takg.nbay.domain.use_case.GetListing
import com.takg.nbay.ui.screens.listings.index.ListingsViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SingleListingViewModel @Inject constructor(
    private val getListing: GetListing,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(SingleListingState())
    val state: State<SingleListingState> = _state

    init {
        savedStateHandle.get<String>("listingId")?.let { listingId ->
            loadListing(listingId)
        }
    }

    private fun loadListing(listingId: String) {
        getListing(id = listingId).onEach { result ->
            when (result) {
                is Resource.Error -> {
                    _state.value = SingleListingState(error = result.message!!)
                }
                is Resource.Loading -> {
                    _state.value = SingleListingState(isLoading = true)
                }
                is Resource.Success -> {
                    _state.value = SingleListingState(listing = result.data!!)
                }
            }

        }.launchIn(viewModelScope)
    }
}