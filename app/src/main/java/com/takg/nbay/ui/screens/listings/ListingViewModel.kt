package com.takg.nbay.ui.screens.listings

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.takg.nbay.common.Resource
import com.takg.nbay.domain.model.Listing
import com.takg.nbay.domain.repository.ListingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


data class ListingsViewState(
    val isLoading: Boolean = false,
    val listings: List<Listing> = emptyList(),
    val error: String = ""
)


@HiltViewModel
class ListingViewModel @Inject constructor(
    private val repository: ListingRepository
) : ViewModel() {
    private val _state = mutableStateOf(ListingsViewState())
    val state: State<ListingsViewState> = _state

    init {
        getListings()
    }

    private fun getListings() {
        repository.getListings().onEach { result ->
            when (result) {
                is Resource.Error -> {
                    _state.value =
                        ListingsViewState(error = result.message ?: "An unexpected error occurred")
                }
                is Resource.Loading -> {
                    _state.value = ListingsViewState(isLoading = true)
                }
                is Resource.Success -> {
                    result.data?.apply {
                        _state.value = ListingsViewState(listings = result.data)
                    }
                }
            }
        }.launchIn(viewModelScope)
    }
}