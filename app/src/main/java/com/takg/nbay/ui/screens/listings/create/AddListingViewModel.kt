package com.takg.nbay.ui.screens.listings.create

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.takg.nbay.common.Resource
import com.takg.nbay.domain.model.ItemCategory
import com.takg.nbay.domain.model.ItemCondition
import com.takg.nbay.domain.use_case.AddListing
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddListingViewModel @Inject constructor(
    private val addListing: AddListing
) : ViewModel() {
    var state by mutableStateOf(AddListingState())

    private val addListingEventChannel = Channel<Resource<Unit>>()
    val addListingEvents = addListingEventChannel.receiveAsFlow()

    fun onEvent(event: AddListingFormEvent) {
        when (event) {
            is AddListingFormEvent.TitleChanged -> {
                state = state.copy(title = event.title)
            }
            is AddListingFormEvent.DescriptionChanged -> {
                state = state.copy(description = event.description)
            }
            is AddListingFormEvent.CategoryChanged -> {
                state = state.copy(category = event.category)
            }
            is AddListingFormEvent.ConditionChanged -> {
                state = state.copy(condition = event.condition)
            }
            is AddListingFormEvent.PriceChanged -> {
                state = state.copy(price = event.price)
            }
            AddListingFormEvent.Submit -> {
                submitData()
            }
        }
    }

    private fun submitData() {
        // TODO: Add validation

        viewModelScope.launch {
            addListing(
                title = state.title,
                description = state.description,
                price = state.price.toDouble(),
                condition = ItemCondition.valueOf(state.condition),
                category = ItemCategory.valueOf(state.category)
            ).collect { result ->
                when (result) {
                    is Resource.Error -> {
                        state = state.copy(isCreating = false)
                        result.message?.apply {
                            addListingEventChannel.send(Resource.Error(result.message))
                        }

                    }
                    is Resource.Loading -> {
                        state = state.copy(isCreating = true)
                    }
                    is Resource.Success -> {
                        addListingEventChannel.send(Resource.Success(Unit))
                    }
                }
            }
        }
    }
}