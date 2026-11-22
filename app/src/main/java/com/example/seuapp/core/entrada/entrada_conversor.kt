package com.example.seuapp.core.entrada

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.seuapp.core.calcularResultado
import com.example.seuapp.core.calculadoraCientifica
import com.example.seuapp.core.converterTemperatura
import android.util.Log

@Composable
fun BoxWithConstraintsScope.BotoesConversorUnidades(
    entrada: String,
    onEntradaChange: (String) -> Unit,
    valorTemperatura: Float,
    nomeTemperatura: String
){
    // Botões
    // Números 1..9, 0
    val numeros = listOf(
        Pair("1", Pair(0.5f, 0.4f)),
        Pair("2", Pair(0.65f, 0.4f)),
        Pair("3", Pair(0.8f, 0.4f)),
        Pair("4", Pair(0.5f, 0.5f)),
        Pair("5", Pair(0.65f, 0.5f)),
        Pair("6", Pair(0.8f, 0.5f)),
        Pair("7", Pair(0.5f, 0.6f)),
        Pair("8", Pair(0.65f, 0.6f)),
        Pair("9", Pair(0.8f, 0.6f)),
        Pair("0", Pair(0.5f, 0.7f))
    )

    for ((digito, pos) in numeros) {
        Button(
            onClick = {
                onEntradaChange(entrada + digito)
            },
            modifier = Modifier.offset(x = maxWidth * pos.first, y = maxHeight * pos.second),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Gray,
                contentColor = Color.White
            ),
            border = BorderStroke(width = 2.dp, Color.DarkGray)
        ) {
            Text("$digito\n")
        }
    }

    // Clear
    Button(
        onClick = {
            onEntradaChange("")
        },
        modifier = Modifier.offset(x = maxWidth * 0.65f, y = maxHeight * 0.7f),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Red,
            contentColor = Color.White
        ),
        border = BorderStroke(width = 2.dp, Color.Black)
    ){
        Text("C\n")
    }

    // Backspace (<-)
    Button(
        onClick = {
            val novaEntrada = if (entrada.takeLast(2) == ".0") {
                entrada.dropLast(2)
            } else {
                entrada.dropLast(1)
            }
            onEntradaChange(novaEntrada)
        },
        modifier = Modifier.offset(x = maxWidth * 0.8f, y = maxHeight * 0.7f),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Red,
            contentColor = Color.White
        ),
        border = BorderStroke(width = 2.dp, Color.Black)
    ) {
        Text("<-\n")
    }

    // Resultado (=)
    Button(
        onClick = {
            val resultado = converterTemperatura(nomeTemperatura, valorTemperatura)
            onEntradaChange(resultado.toString())
            Log.d("Aviso", "O resultado foi enviado com sucesso!")

        },
        modifier = Modifier.offset(x = maxWidth * 0.8f, y = maxHeight * 0.8f),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.DarkGray,
            contentColor = Color.White
        ),
        border = BorderStroke(width = 2.dp, Color.Black)
    ) {
        Text("=\n")
    }
}