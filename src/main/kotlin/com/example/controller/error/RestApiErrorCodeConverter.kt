package com.example.controller.error

import com.example.business.exception.ErrorType
import jakarta.enterprise.context.ApplicationScoped
import java.util.*

@ApplicationScoped
class RestApiErrorCodeConverter(
    private val interfaceCodes: MutableMap<ErrorType, String> = EnumMap(ErrorType::class.java)
) {

    init {
        interfaceCodes[ErrorType.UNKNOWN_ERROR] = RestApiError.UNKNOWN_ERROR
        interfaceCodes[ErrorType.MISSING_ENTITY] = RestApiError.MISSING_ENTITY
        interfaceCodes[ErrorType.INVALID_CHANGE] = RestApiError.INVALID_CHANGE
        interfaceCodes[ErrorType.ALREADY_EXISTS] = RestApiError.ALREADY_EXISTS
    }

    fun convert(errorType: ErrorType) = interfaceCodes[errorType] ?: RestApiError.UNKNOWN_ERROR
}