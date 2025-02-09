package com.example.actividad2_api

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.actividad2_api.ui.theme.Actividad2_ApiTheme
import androidx.navigation.compose.rememberNavController
import com.example.actividad2_api.screen.Header

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() //permite que la UI use toda la pantalla
        setContent { //define la estructura de la app con Jetpack Compose
            Actividad2_ApiTheme {
                val navController = rememberNavController() //crea un controlador de navegación

                //Se usa Scaffold para tener un header comun
                Scaffold(
                    topBar = { Header(modifier = Modifier.fillMaxWidth()) }, // Coloca el Header aquí que es común
                    content = { paddingValues -> //muestra las pantallas y gestiona la navegación
                        ControllerBody(navController = navController, paddingValues = paddingValues)
                    }
                )
            }
        }
    }
}