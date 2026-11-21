package com.example.seuapp.telas

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

// Tema escuro
import com.example.seuapp.core.aplicarTemaEscuro
import com.example.seuapp.core.checar_gesto_voltar

@Composable
fun TelaDeLogin(navController: NavController)
{
    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {

        aplicarTemaEscuro()
        checar_gesto_voltar("sair", navController)

        Button(onClick = {
            // Ao clicar, navega para a rota "principal"
            navController.navigate("principal")
        }, modifier = Modifier.offset(x = maxWidth * 0.38f, y = maxHeight * 0.9f)
        ) {
            Text("Entrar")
        }


    }
}
