package com.takg.nbay.ui.screens.auth.signup

data class SignUpFormState(
    val name: String = "",
    val nameError: String? = null,
    val email: String = "",
    val emailError: String? = null,
    val password: String = "",
    val passwordError: String? = null,
    val terms: Boolean = false,
    val termsError: String? = null,
)
