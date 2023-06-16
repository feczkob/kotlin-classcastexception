package com.example.business.handler

import com.example.business.model.Rating


interface RatingHandler {
    fun loadRatingByUserIdAndVehicleId(userId: Long, vehicleId: Long): Rating?
}