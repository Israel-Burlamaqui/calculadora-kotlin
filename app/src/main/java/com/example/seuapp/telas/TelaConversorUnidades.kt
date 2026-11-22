package com.example.seuapp.telas

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.seuapp.core.aplicarTemaEscuro
import com.example.seuapp.core.checar_gesto_voltar

@Composable
fun TelaConversorUnidades(navController: NavController)
{
    checar_gesto_voltar("principal", navController)
    aplicarTemaEscuro()

    
}