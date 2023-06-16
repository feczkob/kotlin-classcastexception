package com.example.client.persistence.repository

import com.example.client.persistence.entity.Rating
import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class RatingRepository: PanacheRepository<Rating> {
    fun findByUserIdAndVehicleId(): Rating? = null
}