package com.example.actividad2_api.model

import com.google.gson.annotations.SerializedName

    // Clase principal que representa la respuesta JSON
    data class WeatherResponse(
        val cod: String,
        val message: Int,
        val cnt: Int,
        val list: List<WeatherData>,
        val city: City // Agregar esta línea
    )
data class City(
    val name: String
)

    // Clase para representar cada elemento de la lista de pronóstico
    data class WeatherData(
        val dt: Long,
        val main: MainData,
        val weather: List<WeatherDescription>,
        val clouds: Clouds,
        val wind: Wind,
        val visibility: Int,
        val pop: Double,
        val rain: Rain?,
        val snow: Snow?,
        val sys: Sys,
        @SerializedName("dt_txt") val dtTxt: String
    )

    // Datos principales del clima
    data class MainData(
        val temp: Double,
        @SerializedName("feels_like") val feelsLike: Double,
        @SerializedName("temp_min") val tempMin: Double,
        @SerializedName("temp_max") val tempMax: Double,
        val pressure: Int,
        @SerializedName("sea_level") val seaLevel: Int,
        @SerializedName("grnd_level") val grndLevel: Int,
        val humidity: Int
    )

    // Descripción del clima
    data class WeatherDescription(
        val id: Int,
        val main: String,
        val description: String,
        val icon: String
    )

    // Datos de nubosidad
    data class Clouds(
        val all: Int
    )

    // Datos del viento
    data class Wind(
        val speed: Double,
        val deg: Int,
        val gust: Double
    )

    // Datos de lluvia (puede ser nulo)
    data class Rain(
        @SerializedName("3h") val volume: Double
    )

    // Datos de nieve (puede ser nulo)
    data class Snow(
        @SerializedName("3h") val volume: Double
    )

    // Datos del sistema
    data class Sys(
        val pod: String
    )

