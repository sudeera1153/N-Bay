package com.takg.nbay.ui.screens.auth.signup

import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.takg.nbay.common.Resource
import com.takg.nbay.common.ValidationResult
import com.takg.nbay.domain.use_case.CreateUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val createUser: CreateUser
) : ViewModel() {

    var state by mutableStateOf(SignUpFormState())

    private val signUpEventChannel = Channel<Resource<Boolean>>()
    val signUpEvents = signUpEventChannel.receiveAsFlow()

    fun onEvent(event: SignUpFormEvent) {
        when (event) {
            is SignUpFormEvent.AcceptTerms -> {
                state = state.copy(terms = event.isAccepted)
            }
            is SignUpFormEvent.EmailChanged -> {
                state = state.copy(email = event.email)
            }
            is SignUpFormEvent.NameChanged -> {
                state = state.copy(name = event.name)
            }
            is SignUpFormEvent.PasswordChanged -> {
                state = state.copy(password = event.password)
            }
            SignUpFormEvent.Submit -> {
                submitData()
            }
        }
    }

    private fun submitData() {
        val emailResult = validateEmail(state.email)
        val nameResult = validateName(state.name)
        val passwordResult = validatePassword(state.password)
        val termsResult = validateTerms(state.terms)

        val hasError: Boolean = listOf(emailResult, nameResult, passwordResult, termsResult)
            .any { !it.successful }

        state = state.copy(
            nameError = nameResult.errorMessage,
            emailError = emailResult.errorMessage,
            passwordError = passwordResult.errorMessage,
            termsError = termsResult.errorMessage
        )

        if (hasError) {
            return
        }

        viewModelScope.launch {
            createUser(state.name, state.email, state.password).collect { event ->
                signUpEventChannel.send(event)
            }
        }


    }

    private fun validateEmail(email: String): ValidationResult {
        if (email.isBlank()) {
            return ValidationResult.error("The email can't be blank")
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return ValidationResult.error("That's not a valid email")
        }

        return ValidationResult.success()
    }

    private fun validateName(name: String): ValidationResult {
        if (name.isBlank()) {
            return ValidationResult.error("Name can't be blank");
        }

        if (name.length < 2) {
            return ValidationResult.error("Name should be more than 2 letters");
        }

        val hasDigits = name.any { it.isDigit() };

        if (hasDigits) {
            return ValidationResult.error("Name should not include digits")
        }

        return ValidationResult.success();
    }

    private fun validatePassword(password: String): ValidationResult {
        if (password.length < 8) {
            return ValidationResult.error("The password should consist of at least 8 characters")
        }

        val containsLettersAndCharacters =
            password.any { it.isDigit() } && password.any { it.isLetter() }

        if (!containsLettersAndCharacters) {
            return ValidationResult.error(
                "The password should contain at least one letter and digit "
            )
        }

        return ValidationResult.success()
    }

    private fun validateTerms(acceptedTerms: Boolean): ValidationResult {
        if (!acceptedTerms) {
            return ValidationResult.error("Please accept the terms")
        }

        return ValidationResult.success()
    }
}