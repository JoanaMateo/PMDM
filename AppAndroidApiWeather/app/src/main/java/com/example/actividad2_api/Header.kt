package com.example.actividad2_api

import android.app.Activity
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun Header(name: String, modifier: Modifier){
    Box(Modifier.fillMaxSize().padding(16.dp)){
        IconClose(Modifier.align(Alignment.TopEnd))
        ImageLogo(Modifier.align(Alignment.Center))

    }
}

@Composable
fun IconClose(modifier: Modifier) {
    val activity = LocalActivity.current as Activity
    Icon(imageVector = Icons.Default.Close, contentDescription = "close app", modifier = modifier.clickable { activity.finish() })
}
@Composable
fun ImageLogo(modifier: Modifier) {
    Image(painter = painterResource(id= R.drawable.imagenlogo), contentDescription = "logo app", modifier = modifier)
}