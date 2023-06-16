package com.example.controller.validator

import jakarta.enterprise.context.Dependent
import jakarta.validation.ConstraintViolation
import jakarta.validation.Validator

@Dependent
class PatchedRequestValidator<T>(
    private val validator: Validator
) {

    // * https://kdrozd.pl/how-to-perform-a-partial-update-patch-with-explicit-null/

    fun validate(obj: T?, vararg groups: Class<*>?) {
        val validate: Set<ConstraintViolation<T>> = validator.validate(obj, *groups)
        if (validate.isNotEmpty()) {
            throw ValidationException(
                constraintViolations = validate.stream()
                    .map { it.formatConstraintViolationMessage() }
                    .toList()
            )
        }
    }
}

// TODO format this to some class instead of String
fun <T> ConstraintViolation<T>.formatConstraintViolationMessage() =
    "${this.rootBeanClass}: ${this.propertyPath} - ${this.constraintDescriptor.annotation.annotationClass.simpleName}"