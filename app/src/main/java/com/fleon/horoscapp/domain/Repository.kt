package com.fleon.horoscapp.domain

import com.fleon.horoscapp.domain.model.PredictionModel

interface Repository {
    suspend fun getPrediction(sign: String): PredictionModel?
}