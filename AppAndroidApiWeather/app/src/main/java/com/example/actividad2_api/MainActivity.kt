package com.example.actividad2_api

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.actividad2_api.ui.theme.Actividad2_ApiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Actividad2_ApiTheme {
                val navController = rememberNavController()

                //Se usa Scaffold para tener un header comun
                Scaffold(
                    topBar = { Header(Modifier.fillMaxWidth()) }, // Coloca el Header aquí
                    content = { paddingValues ->
                        ControllerBody(navController = navController, paddingValues)
                    }
                )
            }
        }
    }
}
