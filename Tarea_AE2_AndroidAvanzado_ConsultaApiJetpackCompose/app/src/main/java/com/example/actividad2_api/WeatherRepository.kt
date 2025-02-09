package com.example.actividad2_api

import android.util.Log
import com.example.actividad2_api.model.WeatherResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object WeatherRepository {
    //Aquí se maneja la lógica de la conexión a la Api y devuelve los datos procesados
    private val retrofit = Retrofit.Builder() //se crea una instancia de Retrofit
        .baseUrl("https://api.openweathermap.org/data/2.5/")
        .addConverterFactory(GsonConverterFactory.create()) //usa Gson para convertir Json en objetos
        .build()

    private val api = retrofit.create(WeatherApi::class.java) //Crea la api a partir de la interfaz WeatherApi

    suspend fun getWeather(query: String): WeatherResponse? { //funcion tipo promesa
        return withContext(Dispatchers.IO) {
            try {
                val call = api.getAllWeather(query, apiKey = "2f2493f3716ea2df3fe77d0504430fe1") //aquí se hace la llamada a la api
                if (call.isSuccessful) { //si la respuesta es ok devuelve los datos
                    call.body()
                } else { //si no, registra un error y devuelve null
                    Log.e("Error", "Código: ${call.code()} - Respuesta: ${call.errorBody()?.string()}")
                    null
                }
            } catch (e: Exception) {
                Log.e("Error", "Excepción: ${e.message}")
                null
            }
        }
    }
}