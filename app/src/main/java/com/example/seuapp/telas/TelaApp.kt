package com.example.seuapp.telas

import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun TelaApp(navController: NavController)
{
    // Verifica o estado atual do aplicativo e das telas
    val context = LocalActivity.current

    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {

        // Primeira Opção
        Button(onClick = {
            navController.navigate("calculadora")
        }, modifier = Modifier.offset(x = maxWidth * 0.335f, y = maxHeight * 0.4f)
        ) {
            Text("Calculadora\n   simples")
        }

        // Segunda Opção
        Button(onClick = {
            navController.navigate("calculadoraCientifica")
        }, modifier = Modifier.offset(x = maxWidth * 0.335f, y = maxHeight * 0.5f)
        ){
            Text("Calculadora\n   científica")
        }

        // Botão de voltar ao login
        Button(onClick = {

            // Encerra todas as telas e o aplicativo corretamente
            context?.finishAffinity()
        }, modifier = Modifier.offset(x = maxWidth * 0.375f, y = maxHeight * 0.9f)
        ) {
            Text("Fechar")
        }
    }
}