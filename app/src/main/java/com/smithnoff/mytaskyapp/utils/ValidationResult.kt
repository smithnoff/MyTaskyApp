package com.smithnoff.mytaskyapp.utils

sealed class ValidationResult  {
    object Success : ValidationResult()
    data class Failure(val errorMessage: String) : ValidationResult()
}
