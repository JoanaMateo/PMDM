package com.example.actividad2_api

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.navigation.compose.composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController

@Composable
fun ControllerBody(navController: NavHostController, paddingValues: PaddingValues) {
    NavHost(
        navController = navController,
        startDestination = "ScreenLogin", // Pantalla inicial
        modifier = Modifier.padding(paddingValues)
    ) {
        composable("ScreenLogin") {
            Screen_Login(modifier = Modifier.fillMaxSize()) {
                navController.navigate("ScreenApi") // Navega a la pantalla del api
            }
        }
        composable("ScreenApi") {
            Screen_Api()
        }
    }
}