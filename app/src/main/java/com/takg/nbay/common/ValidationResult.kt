package com.takg.nbay.common

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: String? = null
) {
    companion object {
        fun success(): ValidationResult = ValidationResult(true);
        fun error(message: String) = ValidationResult(false, message);
    }
}
