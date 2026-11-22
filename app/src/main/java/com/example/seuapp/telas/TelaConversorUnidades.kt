package com.example.seuapp.telas

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.seuapp.core.aplicarTemaEscuro
import com.example.seuapp.core.checar_gesto_voltar
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Text
import androidx.compose.material3.DropdownMenuItem

@Composable
fun TelaConversorUnidades(navController: NavController) {
    var menuAberto by remember { mutableStateOf(false) }
    var opcaoEscolhida: String = "Escolha ..."

    BoxWithConstraints(modifier = Modifier.fillMaxSize()){
        aplicarTemaEscuro()
        checar_gesto_voltar("principal", navController)


        Button(
            onClick = {
                menuAberto = true
            },
            modifier = Modifier.offset(x = maxWidth * 0.25f, y = maxHeight * 0.25f),
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

        // Lógica do Conversor

    }
}