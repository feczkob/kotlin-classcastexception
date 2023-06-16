package com.example.controller.error

open class RestApiError(
    var errorCode: String = UNKNOWN_ERROR,
    var message: String = UNKNOWN_ERROR,
    var stackTrace: String = "",
    var constraintViolations: List<String>? = null
) {

    companion object {
        const val UNKNOWN_ERROR = "error.unknown"
        const val MISSING_ENTITY = "error.missing.entity"
        const val INVALID_CHANGE = "error.invalid.change"
        const val ALREADY_EXISTS = "error.already.exists"
    }
}

// * builder
fun restApiError(build: RestApiError.() -> Unit) = RestApiError().apply(build)
