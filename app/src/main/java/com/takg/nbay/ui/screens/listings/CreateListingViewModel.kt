package com.takg.nbay.ui.screens.listings

import androidx.lifecycle.ViewModel
import com.takg.nbay.domain.repository.ListingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreateListingViewModel @Inject constructor(
    private val repository: ListingRepository
) : ViewModel() {
}