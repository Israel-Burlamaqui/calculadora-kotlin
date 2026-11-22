package com.example.seuapp.telas

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.seuapp.core.aplicarTemaEscuro
import com.example.seuapp.core.checar_gesto_voltar
import com.example.seuapp.core.entrada.BotoesConversorUnidades

@Composable
fun TelaConversorUnidades(navController: NavController) {
    var menuAberto by remember { mutableStateOf(false) }
    var entrada by remember { mutableStateOf("") }

    var opcaoEscolhida: String = "Escolha ..."
    var resultado: Int = 0
    var valor: Float

    BoxWithConstraints(modifier = Modifier.fillMaxSize()){
        aplicarTemaEscuro()
        checar_gesto_voltar("principal", navController)

        Surface(
            modifier = Modifier.offset(x = maxWidth * 0.1f, y = maxHeight * 0.1f),
            shape = RectangleShape,
            color = Color.Gray,
            border = BorderStroke(
                width = 1.dp,
                color = Color.DarkGray
            )

        ){
            val x = maxWidth * 0.8f
            val y = maxHeight * 0.1f
            Text(entrada,
                modifier = Modifier.size(x,y),
                color = Color.Green)
        }

        Button(
            onClick = {
                menuAberto = true
            },
            modifier = Modifier.offset(x = maxWidth * 0.1f, y = maxHeight * 0.25f),
        ){
            Text(opcaoEscolhida)

            DropdownMenu(
                expanded = menuAberto,
                onDismissRequest = { menuAberto = false}
            ) {
                DropdownMenuItem(
                    text = { Text("Celsius")},
                    onClick = {
                        menuAberto = false
                        opcaoEscolhida = "Celsius"
                        // Ação ao pressionar
                    }
                )

                DropdownMenuItem(
                    text = {Text("Fahrenheit")},
                    onClick = {
                        menuAberto = false
                        opcaoEscolhida = "Fahrenheit"
                        // Ação ao pressionar
                    }
                )
            }
        }

        if (entrada == ""){
            valor = 0f
        } else{
            valor = entrada.toFloat()
        }

        // Lógica do Conversor
        BotoesConversorUnidades(
            entrada,
            onEntradaChange = {entrada = it},
            valor,

            opcaoEscolhida
        )


    }
}