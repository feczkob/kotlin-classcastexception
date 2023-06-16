package com.example.controller.response

import com.example.controller.Constants.Companion.RATING
import jakarta.ws.rs.Path
import org.eclipse.microprofile.openapi.annotations.media.Schema

@Path(RATING)
@Schema(description = "Class representing a rating response")
data class Rating(
    @field:Schema(description = "Id of the rating")
    var id: Long = 0,
    @field:Schema(description = "Value of the rating")
    var value: Int = 0,
    @field:Schema(description = "Comment of the rating")
    var comment: String? = null,
    @field:Schema(description = "Id of the user submitting the rating")
    var userId: Long = 0,
    @field:Schema(description = "Id of the vehicle for which the rating is being submitted")
    var vehicleId: Long = 0,
)