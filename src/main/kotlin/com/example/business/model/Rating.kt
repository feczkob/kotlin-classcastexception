package com.example.business.model


import com.example.application.logger.MethodLogger
import com.example.business.exception.BusinessException
import com.example.business.exception.ErrorType
import com.example.business.handler.RatingHandler
import jakarta.enterprise.context.Dependent
import jakarta.inject.Inject

@Dependent
class Rating (
    var id: Long? = null,
    var value: Int = 0,
    var comment: String? = null,
    var userId: Long = 0,
    var vehicleId: Long = 0,
) {

    @Transient
    @Inject
    private lateinit var ratingHandler: RatingHandler

    @MethodLogger
    fun add() : Rating {
        return if(ratingHandler.loadRatingByUserIdAndVehicleId(userId, vehicleId) != null) {
            throw BusinessException(
                ErrorType.ALREADY_EXISTS, "User with user id $userId" +
                    " has already submitted a rating for vehicle with id $vehicleId")
        } else {
            save()
        }
    }

    private fun save() = this


}