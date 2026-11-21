package com.example.seuapp.core

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color


@Composable
fun aplicarTemaEscuro() {
    if (isSystemInDarkTheme() == true) {

        Surface(
            Modifier.fillMaxSize(),
            color = Color.Black,
            contentColor = Color.Black
        )
        {

        }
    } else
    {
        Surface(
            Modifier.fillMaxSize(),
            color = Color.White,
            contentColor =  Color.White
        )
        {

        }
    }
}