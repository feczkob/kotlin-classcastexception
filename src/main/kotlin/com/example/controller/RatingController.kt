package com.example.controller

import com.example.application.logger.MethodLogger
import com.example.business.model.Rating
import com.example.controller.Constants.Companion.RATING
import com.example.controller.converter.RatingMapper
import jakarta.validation.Valid
import jakarta.validation.constraints.NotNull
import jakarta.ws.rs.Consumes
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import jakarta.ws.rs.core.UriBuilder
import org.eclipse.microprofile.openapi.annotations.Operation

@Path(RATING)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@MethodLogger
class RatingController(
    private val ratingMapper: RatingMapper,
    private val rating: Rating,
) {

    @Operation(description = "Add rating")
    @POST
    fun addRating(@Valid @NotNull rating: com.example.controller.request.Rating): Response {
        ratingMapper.toBusiness(rating, this.rating)
        val ratingAdded = this.rating.add()
        val processedUri = UriBuilder.fromResource(com.example.controller.response.Rating::class.java)
            .run {
                path(Constants.RATING_ID_PARAM)
                build(ratingAdded.id)
            }
        return Response.created(processedUri).entity(ratingMapper.toResponse(ratingAdded)).build()
    }
}