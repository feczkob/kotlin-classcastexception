package com.example.controller.filter

import com.example.application.bean.RequestScopedBean
import com.example.application.bean.WithHttpServerRequestAndUriInfo
import com.example.application.bean.WithRequestScopedBean
import io.quarkus.logging.Log
import io.quarkus.vertx.http.runtime.CurrentVertxRequest
import io.vertx.core.http.HttpServerRequest
import jakarta.enterprise.context.ApplicationScoped
import jakarta.ws.rs.container.ContainerRequestContext
import jakarta.ws.rs.container.ContainerRequestFilter
import jakarta.ws.rs.core.UriInfo
import jakarta.ws.rs.ext.Provider

@Provider
@ApplicationScoped
class RequestFilter(
    override val request: HttpServerRequest,
    override val uriInfo: UriInfo,
    override val requestScopedBean: RequestScopedBean,
    private val currentVertxRequest: CurrentVertxRequest
) : ContainerRequestFilter, WithRequestScopedBean,
    WithHttpServerRequestAndUriInfo {

    override fun filter(requestContext: ContainerRequestContext) =
        Log.info(logCorrelationId() +
                logRequestOrigin() + " arrived: " +
                logMethodNameWithUriInfo())

}