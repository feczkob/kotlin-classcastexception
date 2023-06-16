package com.example.controller.request

import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotNull
import org.eclipse.microprofile.openapi.annotations.media.Schema

@Schema(description = "Class representing a rating request")
data class Rating(
    @field:NotNull
    @field:Min(value = 1)
    @field:Max(value = 10)
    @field:Schema(description = "Value of the rating")
    var value: Int = 0,
    @field:Schema(description = "Comment of the rating")
    var comment: String? = null,
    @field:Schema(description = "Id of the user submitting the rating")
    var userId: Long = 0,
    @field:Schema(description = "Id of the vehicle for which the rating is being submitted")
    var vehicleId: Long = 0,
)