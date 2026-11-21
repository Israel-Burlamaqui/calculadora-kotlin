package com.example.seuapp.telas

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.seuapp.core.aplicarTemaEscuro
import com.example.seuapp.core.checar_gesto_voltar
import com.example.seuapp.core.entrada.BotoesCalculadoraSimples

@Composable
fun TelaCalculadora(navController: NavController)
{
    var entradaNumeroInteiro by remember { mutableStateOf("") }

    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {

        aplicarTemaEscuro()
        checar_gesto_voltar("principal", navController)

        // Display
        Surface(color = Color.DarkGray,
            modifier = Modifier.offset(x = maxWidth * 0.1f, y = maxHeight * 0.1f),
            border = BorderStroke(
                width = 1.dp,
                Color.Black
            )
            )
        {
            val x = maxWidth * 0.8f
            val y = maxHeight * 0.2f
            Text("$entradaNumeroInteiro",
                modifier = Modifier.size(x, y),
                color = Color.White
            )
        }

        // Desenhar os botões e processar as entradas do touchscreen em uma única chamada
        BotoesCalculadoraSimples(
            entrada = entradaNumeroInteiro,
            onEntradaChange = { entradaNumeroInteiro = it },
            navController = navController
        )
    }
}
