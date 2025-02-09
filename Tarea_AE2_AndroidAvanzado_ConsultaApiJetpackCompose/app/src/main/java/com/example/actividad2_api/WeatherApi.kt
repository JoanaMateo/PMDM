package com.example.actividad2_api

import com.example.actividad2_api.model.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    //Se definen los endpoints de la APi
    @GET("forecast") // 👈 Aquí defines la parte final de la URL
    suspend fun getAllWeather(
        //cada parámetro de la consulta
        @Query("q") city: String,
        @Query("lang") lang: String = "es",
        @Query("units") units: String = "metric",
        @Query("mode") mode: String = "json",
        @Query("appid") apiKey: String
    ): Response<WeatherResponse>
}