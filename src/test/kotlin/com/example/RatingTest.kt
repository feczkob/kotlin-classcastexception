package com.example

import com.example.controller.Constants.Companion.RATING
import com.example.controller.request.Rating
import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import io.restassured.http.ContentType
import org.junit.jupiter.api.Test

@QuarkusTest
class RatingTest {

    companion object {
        private const val VEHICLE_ID_1: Long = 1001
    }

    @Test
    fun whenPostValidRatingThenWtf() {
        given()
            .`when`()
            .contentType(ContentType.JSON)
            .body(rating {
                value = 10
                comment = "comment"
                userId = 10
                vehicleId = VEHICLE_ID_1
            })
            .post(RATING)
            .then()
            .statusCode(500)
    }
}

fun rating(build: Rating.() -> Unit) = Rating().apply(build)