package com.example.controller.error

import com.example.application.bean.RequestScopedBean
import com.example.application.bean.WithHttpServerRequestAndUriInfo
import com.example.application.bean.WithRequestScopedBean
import com.example.business.exception.BusinessException
import com.example.controller.validator.ValidationException
import com.example.controller.validator.formatConstraintViolationMessage
import io.quarkus.logging.Log
import io.vertx.core.http.HttpServerRequest
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import jakarta.validation.ConstraintViolationException
import jakarta.ws.rs.core.Response
import jakarta.ws.rs.core.UriInfo
import jakarta.ws.rs.ext.ExceptionMapper
import jakarta.ws.rs.ext.Provider

abstract class ExceptionHandler(
) : WithRequestScopedBean, WithHttpServerRequestAndUriInfo {

    @Inject
    override lateinit var request: HttpServerRequest
    @Inject
    override lateinit var uriInfo: UriInfo
    @Inject
    override lateinit var requestScopedBean: RequestScopedBean

    protected fun log(e: Exception) {
        Log.info(logCorrelationId() +
                logRequestOrigin() + " failed: " +
                logMethodNameWithUriInfo())
        Log.error(e.stackTraceToString())
    }
}

@Provider
@ApplicationScoped
class BusinessExceptionHandler(
    private val errorCodeConverter: RestApiErrorCodeConverter,
) : ExceptionMapper<BusinessException>, ExceptionHandler() {

    override fun toResponse(exception: BusinessException): Response {
       log(exception)

        return Response
            .status(exception.status)
            .entity(restApiError {
                errorCode = errorCodeConverter.convert(exception.errorType)
                message = exception.message
                stackTrace = exception.stackTraceToString()
            })
            .build()
    }
}

@Provider
@ApplicationScoped
class ManualValidationExceptionHandler : ExceptionMapper<ValidationException>, ExceptionHandler() {

    override fun toResponse(exception: ValidationException): Response {
        log(exception)

        return Response
            .status(Response.Status.BAD_REQUEST)
            .entity(restApiError {
                errorCode = exception.errorCode
                message = exception.message
                stackTrace = exception.stackTraceToString()
                constraintViolations = exception.constraintViolations
            })
            .build()
    }
}

@Provider
@ApplicationScoped
class AutomaticValidationExceptionHandler : ExceptionMapper<ConstraintViolationException>, ExceptionHandler() {

    override fun toResponse(exception: ConstraintViolationException): Response {
        log(exception)

        return Response
            .status(Response.Status.BAD_REQUEST)
            .entity(restApiError {
                errorCode = ValidationException.ERROR_CODE
                message = ValidationException.MESSAGE
                stackTrace = exception.stackTraceToString()
                constraintViolations = exception.constraintViolations
                    .map { it.formatConstraintViolationMessage() }
                    .toList()
            })
            .build()
    }
}

@Provider
@ApplicationScoped
class BasicExceptionHandler : ExceptionMapper<Exception>, ExceptionHandler() {

    override fun toResponse(exception: Exception): Response {
        log(exception)

        return Response
            .status(Response.Status.INTERNAL_SERVER_ERROR)
            .entity(restApiError {
                stackTrace = exception.stackTraceToString()
            })
            .build()
    }
}