package com.takg.nbay.ui.screens.listings.create

sealed class AddListingFormEvent {
    data class TitleChanged(val title: String) : AddListingFormEvent()
    data class DescriptionChanged(val description: String) : AddListingFormEvent()
    data class CategoryChanged(val category: String) : AddListingFormEvent()
    data class ConditionChanged(val condition: String) : AddListingFormEvent()
    data class PriceChanged(val price: String) : AddListingFormEvent()

    object Submit : AddListingFormEvent()
}