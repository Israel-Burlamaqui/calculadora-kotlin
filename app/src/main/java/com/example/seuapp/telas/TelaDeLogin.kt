package com.example.seuapp.telas

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.seuapp.core.aplicarTemaEscuro
import com.example.seuapp.core.checar_gesto_voltar

@Composable
fun TelaDeLogin(navController: NavController)
{
    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {

        aplicarTemaEscuro()
        checar_gesto_voltar("sair", navController)

        Surface(
            modifier = Modifier.offset(x = maxWidth * 0.33f, y = maxHeight * 0.25f),
            contentColor = Color.Black,
            color = Color.Transparent,
        ){
            if (isSystemInDarkTheme() == false) {
                Text(
                    "Calculadora \nKotlin",
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center,
                    color = Color.Black
                )
            } else
            {
                Text(
                    "Calculadora \nKotlin",
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center,
                    color = Color.White
                )
            }
        }

        Button(onClick = {
            // Ao clicar, navega para a rota "principal"
            navController.navigate("principal")
        }, modifier = Modifier.offset(x = maxWidth * 0.38f, y = maxHeight * 0.9f)
        ) {
            Text("Entrar")
        }
    }
}
