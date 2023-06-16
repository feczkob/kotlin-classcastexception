package com.example.application.bean

import com.example.controller.Constants.Companion.CORRELATION_ID
import io.vertx.core.http.HttpServerRequest
import jakarta.enterprise.context.RequestScoped
import jakarta.ws.rs.core.UriInfo
import java.util.*

@RequestScoped
class RequestScopedBean(
    val correlationId: String = UUID.randomUUID().toString()
)

interface WithRequestScopedBean {

    val requestScopedBean: com.example.application.bean.RequestScopedBean
    fun logCorrelationId() = "[${CORRELATION_ID}: ${requestScopedBean.correlationId}] "
}

interface WithHttpServerRequestAndUriInfo {

    val request: HttpServerRequest
    val uriInfo: UriInfo

    fun logRequestOrigin() = "Request from ${request.remoteAddress()}"
    fun logMethodNameWithUriInfo() = "${request.method().name()} ${uriInfo.path}"
}