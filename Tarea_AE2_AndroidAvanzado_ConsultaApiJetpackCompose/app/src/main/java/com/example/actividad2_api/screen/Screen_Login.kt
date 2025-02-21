package com.example.actividad2_api.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun Screen_Login(modifier: Modifier = Modifier, navController: NavController){
    Box(Modifier.fillMaxSize().padding(16.dp)){
        Login(
            modifier = Modifier.align(Alignment.Center),
            onLoginClick = { navController.navigate("ScreenApi") } // navega a la siguiente pantalla al pulsar el botón.
        )
    }
}

@Composable
fun Login(modifier: Modifier = Modifier, onLoginClick: () -> Unit) {
    var email by rememberSaveable { mutableStateOf("") }
    var pass by rememberSaveable { mutableStateOf("") }
    val isLoginEnable = email.isNotBlank() && pass.isNotBlank() // Habilita el botón solo si hay datos válidos
    Column (modifier = modifier) {
        InputMail(email = email, onTextChanged = { email = it.lowercase() })
        Spacer(modifier = Modifier.size(16.dp))
        InputPass(pass = pass, onTextChanged = { pass = it.lowercase() })
        Spacer(modifier = Modifier.size(16.dp))
        ForgotPass(modifier = Modifier.align(Alignment.End))
        Spacer(modifier = Modifier.size(16.dp))
        LoginButton(loginEnable = isLoginEnable, onLoginClick = onLoginClick)
    }
}

@Composable
fun InputMail(email: String, onTextChanged: (String) -> Unit) {
    TextField(value = email, onValueChange = {onTextChanged(it)},
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = "Email") },
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email) // Mejora usabilidad
    )
}
@Composable
fun InputPass(pass: String, onTextChanged: (String) -> Unit) {
    TextField(value = pass, onValueChange = {onTextChanged(it)},
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = "Password") },
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password) // Mejora usabilidad
    )
}
@Composable
fun ForgotPass(modifier: Modifier = Modifier) {
    Text(text = "Forgot password",
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        modifier = modifier
    )
}
@Composable
fun LoginButton(loginEnable: Boolean, onLoginClick: () -> Unit) {
    Button(
        onClick = onLoginClick,
        enabled = loginEnable,
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors()
    ){
        Text(text = "Log In")
    }

}

