package com.example.actividad2_api.screen
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.actividad2_api.WeatherRepository
import kotlinx.coroutines.launch

//Pantalla donde el usuario ingresa su ciudad y ve la temperatura.
@Composable
fun WeatherScreen(navController: NavController) {
    var cityName by remember { mutableStateOf("") }
    var temperature by remember { mutableStateOf("") }
    var userInput by remember { mutableStateOf("") } // Input del usuario
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current  // Obtener el contexto para el Toast


    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        // Input para ingresar la ciudad
        TextField(
            value = userInput,
            onValueChange = { userInput = it.lowercase() },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Escribe una ciudad") }
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Botón para buscar los datos de la api
        Button(
            onClick = {
                coroutineScope.launch {
                    val result = WeatherRepository.getWeather(userInput)
                    result?.let {
                        cityName = it.city.name
                        //val tempCelsius = it.list.first().main.temp - 273.15
                        temperature = "%.2f°C".format(it.list.first().main.temp)
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Consultar Clima")
        }
        Spacer(modifier = Modifier.height(16.dp))

        // Mostrar el resultado si hay datos
        if (cityName.isNotEmpty()) {
            Text(text = "Ciudad: $cityName", fontSize = 20.sp)
            Text(text = "Temperatura: $temperature", fontSize = 18.sp)
            Spacer(modifier = Modifier.height(16.dp))
        }else{
            Toast.makeText(context, "No se encontraron datos", Toast.LENGTH_SHORT).show()
        }

        // Botón para volver atrás
        Button(
            onClick = { navController.popBackStack() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Volver")
        }
    }
}