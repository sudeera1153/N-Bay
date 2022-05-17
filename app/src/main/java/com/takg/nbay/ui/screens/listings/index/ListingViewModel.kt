package com.takg.nbay.ui.screens.listings.index

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.takg.nbay.common.Resource
import com.takg.nbay.domain.use_case.GetListings
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class ListingViewModel @Inject constructor(
    private val getListings: GetListings
) : ViewModel() {
    private val _state = mutableStateOf(ListingsViewState())
    val state: State<ListingsViewState> = _state

    init {
        loadListings()
    }

    private fun loadListings() {
        getListings().onEach { result ->
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