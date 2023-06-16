package com.example.controller.validator

class ValidationException(
    val errorCode: String = ERROR_CODE,
    override val message: String = MESSAGE,
    val constraintViolations: List<String>
) : Exception() {

    companion object {
        const val ERROR_CODE = "error.validation"
        const val MESSAGE = "Validation error!"
    }
}