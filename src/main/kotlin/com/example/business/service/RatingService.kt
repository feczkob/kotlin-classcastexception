package com.example.business.service


import com.example.application.logger.MethodLogger
import com.example.business.handler.RatingHandler
import com.example.business.model.Rating
import com.example.business.service.converter.CycleAvoidingMappingContext.context
import com.example.business.service.converter.RatingMapper
import com.example.client.persistence.repository.RatingRepository
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
@MethodLogger
class RatingService(
    private val ratingMapper: RatingMapper,
    private val ratingRepository: RatingRepository,
    private val rating: Rating,
) : RatingHandler {

    // https://github.com/Kotlin/kotlinx.coroutines/issues/2838
    // https://youtrack.jetbrains.com/issue/KT-47527
    // TODO why does not this work??
//    override fun loadRatingByUserIdAndVehicleId(userId: Long, vehicleId: Long) =
//        ratingRepository.findByUserIdAndVehicleId(userId, vehicleId)
//            ?.let { ratingMapper.toBusiness(it, rating, context()) }
    override fun loadRatingByUserIdAndVehicleId(userId: Long, vehicleId: Long): Rating? =
        ratingRepository.findByUserIdAndVehicleId()
            ?.let { ratingMapper.toBusiness(it, rating, context()) }

}