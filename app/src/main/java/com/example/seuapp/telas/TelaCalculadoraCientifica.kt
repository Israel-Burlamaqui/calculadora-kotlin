package com.example.seuapp.telas

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.seuapp.core.aplicarTemaEscuro
import com.example.seuapp.core.checar_gesto_voltar
import com.example.seuapp.core.entrada.BotoesCalculadoraCientifica

@Composable
fun TelaCalculadoraCientifica(navController: NavController)
{
    var entradaNumero by remember { mutableStateOf("") }

    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {

        aplicarTemaEscuro()
        checar_gesto_voltar("principal", navController)

        // Display
        Surface(color = Color.DarkGray,
            modifier = Modifier.offset(x = maxWidth * 0.1f, y = maxHeight * 0.1f)
        ) {
            val tamanhoTextoX = maxWidth * 0.8f
            val tamanhoTextoY = maxHeight * 0.2f

            Text("$entradaNumero",
                modifier = Modifier.size(tamanhoTextoX, tamanhoTextoY),
                color = Color.Green
            )
        }

        // Desenhar os botões e processar as entradas do touchscreen em uma única chamada
        BotoesCalculadoraCientifica(
            entrada = entradaNumero,
            onEntradaChange = { entradaNumero = it },
            navController = navController
        )
    }
}
