package com.example.actividad2_api.screen

import android.app.Activity
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.actividad2_api.R
//Encabezado de la aplicación. Encabezado reutilizable con logo y botón de cierre

@Composable
fun Header(modifier: Modifier = Modifier){
    Box(Modifier
        .fillMaxWidth() // No ocupará todo el tamaño de la pantalla
        .padding(16.dp)
    ){ //Los elementos que tiene el box
        IconClose(Modifier.align(Alignment.TopEnd)) //botón cierre
        ImageLogo(Modifier.align(Alignment.Center)) //imagen logo app

    }
}

@Composable
fun IconClose(modifier: Modifier) {
    val context = LocalContext.current // obtiene la actividad actual
    val activity = context as? Activity //convierte el convierte el contexto en Activity. Con ? evitamos errores si no es una actividad
    Icon(imageVector = Icons.Default.Close,
        contentDescription = "close app",
        modifier = modifier.clickable { activity?.finish() } // ?= Evita errores si `activity` es null
    )
}
@Composable
fun ImageLogo(modifier: Modifier) {
    Image(
        painter = painterResource(id= R.drawable.imagenlogo),
        contentDescription = "logo app",
        modifier = modifier
    )
}