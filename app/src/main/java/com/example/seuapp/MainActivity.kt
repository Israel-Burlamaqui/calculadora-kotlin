package com.example.seuapp

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Button
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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.size

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
fun TelaDeLogin(navController: NavController)
{
    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
    Button(onClick = {
        // Ao clicar, navega para a rota "principal"
        navController.navigate("principal")
    }, modifier = Modifier.offset(x = maxWidth * 0.38f, y = maxHeight * 0.9f)
    ) {
        Text("Entrar")
    }
    }
}

@Composable
fun TelaApp(navController: NavController) {

    // Verifica o estado atual do aplicativo e das telas
   val context = LocalActivity.current as? Activity

   BoxWithConstraints(modifier = Modifier.fillMaxSize()) {

    // Primeira Opção
    Button(onClick = {
        navController.navigate("calculadora")
    }, modifier = Modifier.offset(x = maxWidth * 0.335f, y = maxHeight * 0.4f)
        ) {
        Text("Calculadora\n   simples")
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

@Composable
fun TelaCalculadora(navController: NavController)
{
    var numeroInteiro1 by remember { mutableStateOf(0) }
    var numeroInteiro2 by remember { mutableStateOf(0) }

    var entradaNumeroInteiro by remember { mutableStateOf("") }

    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {

        // Display
        Surface(color = Color.DarkGray,
            modifier = Modifier.offset(x = maxWidth * 0.1f, y = maxHeight * 0.1f),

            )
        {
            val x = maxWidth * 0.8f
            val y = maxHeight * 0.2f
            Text("$entradaNumeroInteiro",
                modifier = Modifier.size(x, y)
            )



        }

        // Soma
        Button(onClick = {
            println("Soma")
        }, modifier = Modifier.offset(x = maxWidth * 0.1f, y = maxHeight * 0.3f)
        ) {
            Text("+")
        }

        // Diferença
        Button(onClick = {
            println("Diferença")
        }, modifier = Modifier.offset(x = maxWidth * 0.25f, y = maxHeight * 0.3f)
        ) {
            Text("-")
        }

        // Produto
        Button(onClick = {
            println("Produto")
        }, modifier = Modifier.offset(x = maxWidth * 0.4f, y = maxHeight * 0.3f)
        ) {
            Text("×")
        }

        // Razão
        Button(onClick = {
            println("Razão")
        }, modifier = Modifier.offset(x = maxWidth * 0.55f, y = maxHeight * 0.3f)
        ) {
            Text("÷")
        }

        // Voltar
        Button(onClick = {
            navController.navigate("principal")
        }, modifier = Modifier.offset(x = maxWidth * 0.38f, y = maxHeight * 0.9f)
        ) {
            Text("Voltar")
        }

        // ================================================
        // Entrada de números



    }
}
/*
* Não, mas obrigado. O Android SDK possui alguma função para encerrar
  forçadamente o aplicativo, como o exit(0) em C/C++ ?
* */

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