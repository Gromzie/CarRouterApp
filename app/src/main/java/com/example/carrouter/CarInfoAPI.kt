package com.example.carrouter

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CarInfoAPI {
    @GET("HistoriskFordon/v0.1/")
    fun getCarInfo(
        @Query("\$filter") year: String
    ): Call<CarResponse>
}

data class CarResponse(
    val someField: String // Define fields based on API response
)