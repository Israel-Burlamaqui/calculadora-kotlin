package com.example.seuapp.core

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import kotlin.system.exitProcess

@Composable
fun checar_gesto_voltar(nomeTelaAnterior: String, navController: NavController)
{
    BackHandler(enabled = true)
    {
        if (nomeTelaAnterior != "sair")
        {
            navController.navigate(nomeTelaAnterior)
        } else
        {
            exitProcess(0)
        }
    }
}