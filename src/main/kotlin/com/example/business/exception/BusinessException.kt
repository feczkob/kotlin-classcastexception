package com.example.business.exception

import jakarta.ws.rs.core.Response

class BusinessException(
    val errorType: ErrorType,
    override val message: String
) : Exception() {
    val status: Response.Status
        get() {
            return when(errorType) {
                ErrorType.UNKNOWN_ERROR -> Response.Status.INTERNAL_SERVER_ERROR
                ErrorType.MISSING_ENTITY -> Response.Status.NOT_FOUND
                ErrorType.INVALID_CHANGE -> Response.Status.BAD_REQUEST
                ErrorType.ALREADY_EXISTS -> Response.Status.CONFLICT
            }
        }
}