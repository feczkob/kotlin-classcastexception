package com.example.application.logger

import jakarta.interceptor.InterceptorBinding

@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.CLASS,
)
@InterceptorBinding
annotation class MethodLogger