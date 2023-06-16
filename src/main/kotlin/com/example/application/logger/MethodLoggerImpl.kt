@file:Suppress("unused")

package com.example.application.logger

import com.example.application.bean.RequestScopedBean
import com.example.application.bean.WithRequestScopedBean
import io.quarkus.logging.Log
import jakarta.annotation.Priority
import jakarta.interceptor.AroundInvoke
import jakarta.interceptor.Interceptor
import jakarta.interceptor.InvocationContext

@Interceptor
@MethodLogger
@Priority(0)
class MethodLoggerImpl(
    override val requestScopedBean: RequestScopedBean,
) : WithRequestScopedBean {

    @AroundInvoke
    fun logInvocation(context: InvocationContext): Any {
        val method = context.method
        val methodClass = method.declaringClass.name
        val methodName = method.name
        val parameterValues = context.parameters.mapIndexed { index, value ->
            val parameterName = method.parameters[index].name
            "$parameterName = $value"
        }.joinToString(", ")

        Log.info(logCorrelationId() +
                "Method starts: $methodClass.$methodName($parameterValues)")

        val ret: Any? = context.proceed()

        Log.info(logCorrelationId() +
                "Method ends: $methodClass.$methodName => $ret")
        return ret ?: Unit
    }
}