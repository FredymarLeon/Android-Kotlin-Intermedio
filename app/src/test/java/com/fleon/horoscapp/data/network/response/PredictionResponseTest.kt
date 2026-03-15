package com.fleon.horoscapp.data.network.response

import io.kotlintest.shouldBe
import org.junit.Test

class PredictionResponseTest {
    @Test
    fun `toDomain should return a correct PredictionModel`() {
        //Given
        val horoscopeResponse = PredictionResponse("15 de marzo", "prediction", "taurus")

        //When
        val predictionModel = horoscopeResponse.toDomain()

        //then
        predictionModel.sign shouldBe horoscopeResponse.sign
        predictionModel.horoscope shouldBe horoscopeResponse.horoscope
    }
}