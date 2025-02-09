package com.example.actividad2_api

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.actividad2_api.screen.Screen_Login
import com.example.actividad2_api.screen.WeatherScreen

//Para manejar la navegación entre pantallas
@Composable
fun ControllerBody(navController: NavHostController, paddingValues: PaddingValues) {
    NavHost( //Se define la estructura de navegación
        navController = navController,
        startDestination = "ScreenLogin", // Pantalla inicial
        modifier = Modifier.padding(paddingValues)
    ) {
        composable("ScreenLogin") { //Carga la pantalla de ScreenLogin
            Screen_Login(modifier = Modifier.fillMaxSize(), navController = navController)
        }
        composable("ScreenApi") { //Carga la pantalla donde se muestran los datos de la Api.
            WeatherScreen(navController)
        }
    }
}