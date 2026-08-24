package com.example.seuapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

// Importar telas
import com.example.seuapp.telas.TelaApp
import com.example.seuapp.telas.TelaCalculadora
import com.example.seuapp.telas.TelaDeLogin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Expande a tela do app para além do entalhe
        //  da câmera, produzindo imersão completa:
        enableEdgeToEdge()

        // O bloco de código abaixo seria a nossa amada "int main() { }"
        // do C e C++
        setContent {
            MainLoop()
        }
    }
}

@Composable
fun MainLoop()
{
    // Cria e lembra o controlador de navegação
    val navController = rememberNavController()

    // Define o "mapa" de navegação
    NavHost(navController = navController, startDestination ="login")
    {
        // Rota para a tela de login
        composable("login")
        {
            TelaDeLogin(navController = navController)
        }

        // Rota para a tela principal
        composable("principal")
        {
            TelaApp(navController = navController)
        }

        composable("calculadora")
        {
            TelaCalculadora(navController = navController)
        }
    }
}