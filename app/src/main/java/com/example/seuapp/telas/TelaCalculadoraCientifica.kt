package com.example.seuapp.telas

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import com.example.seuapp.core.calculadoraCientifica

@Composable
fun TelaCalculadoraCientifica(navController: NavController)
{
    var entradaNumero by remember { mutableStateOf("") }

    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {

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

        // Soma / Sinal positivo (Gemini):
        //   - Após dígito ou '.': adiciona '+' como operador
        //   - Após '-' de sinal: remove o '-' (torna o número positivo)
        //   - Após outro operador ou expressão vazia: não faz nada (positivo é o padrão)
        Button(onClick = {
            val ultimoChar = entradaNumero.trimEnd().lastOrNull()
            when {
                ultimoChar == null || ultimoChar in listOf('+', '×', '÷') -> { }
                ultimoChar == '-' ->
                    entradaNumero = entradaNumero.trimEnd().dropLast(1)
                else ->
                    entradaNumero += " + "
            }
        }, modifier = Modifier.offset(x = maxWidth * 0.35f, y = maxHeight * 0.4f),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.DarkGray,
                contentColor = Color.White
            ),
            border = BorderStroke(width = 2.dp, Color.Black)
        ) {
            Text("+\n")
        }

        // Diferença / Sinal negativo (Gemini):
        //   - Expressão vazia ou após operador ×÷: adiciona '-' como sinal do próximo número
        //   - Após '+' de operador: substitui por '-'
        //   - Após '-' de sinal: remove (toggle → positivo)
        //   - Após dígito ou '.': adiciona '-' como operador
        Button(onClick = {
            val expressaoTrim = entradaNumero.trimEnd()
            val ultimoChar = expressaoTrim.lastOrNull()
            when {
                ultimoChar == null || ultimoChar in listOf('×', '÷') ->
                    entradaNumero = entradaNumero.trimEnd() + "-"
                ultimoChar == '+' ->
                    entradaNumero = expressaoTrim.dropLast(1) + "-"
                ultimoChar == '-' ->
                    entradaNumero = expressaoTrim.dropLast(1)
                else ->
                    entradaNumero += " - "
            }
        }, modifier = Modifier.offset(x = maxWidth * 0.35f, y = maxHeight * 0.5f),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.DarkGray,
                contentColor = Color.White
            ),
            border = BorderStroke(width = 2.dp, Color.Black)
        ) {
            Text("-\n")
        }

        // Produto
        Button(onClick = {
            println("Produto")
            entradaNumero += " × "
        }, modifier = Modifier.offset(x = maxWidth * 0.35f, y = maxHeight * 0.6f),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.DarkGray,
                contentColor = Color.White
            ),
            border = BorderStroke(width = 2.dp, Color.Black)
        ) {
            Text("×\n")
        }

        // Razão
        Button(onClick = {
            println("Razão")
            entradaNumero += " ÷ "
        }, modifier = Modifier.offset(x = maxWidth * 0.35f, y = maxHeight * 0.7f),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.DarkGray,
                contentColor = Color.White
            ),
            border = BorderStroke(width = 2.dp, Color.Black)
        ) {
            Text("÷\n")
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

        Button(onClick = {
            entradaNumero += "1"
        }, modifier = Modifier.offset(x = maxWidth * 0.5f, y = maxHeight * 0.4f),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Gray,
                contentColor = Color.White
            )
        ) {
            Text("1\n")
        }

        Button(onClick = {
            entradaNumero += "2"
        }, modifier = Modifier.offset(x = maxWidth * 0.65f, y = maxHeight * 0.4f),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Gray,
                contentColor = Color.White
            )
        ) {
            Text("2\n")
        }

        Button(onClick =  {
            entradaNumero += "3"
        }, modifier = Modifier.offset(x = maxWidth * 0.8f, y = maxHeight * 0.4f),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Gray,
                contentColor = Color.White
            )
        ) {
            Text("3\n")
        }

        Button(onClick = {
            entradaNumero += "4"
        }, modifier = Modifier.offset(x = maxWidth * 0.5f, y = maxHeight * 0.5f),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Gray,
                contentColor = Color.White
            )
        ) {
            Text("4\n")
        }

        Button(onClick = {
            entradaNumero += "5"
        }, modifier = Modifier.offset(x = maxWidth * 0.65f, y = maxHeight * 0.5f),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Gray,
                contentColor = Color.White
            )
        ) {
            Text("5\n")
        }

        Button(onClick = {
            entradaNumero += "6"
        }, modifier = Modifier.offset(x = maxWidth * 0.8f, y = maxHeight * 0.5f),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Gray,
                contentColor = Color.White
            )
        ) {
            Text("6\n")
        }

        Button(onClick = {
            entradaNumero += "7"
        }, modifier = Modifier.offset(x = maxWidth * 0.5f, y = maxHeight * 0.6f),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Gray,
                contentColor = Color.White
            )
        ) {
            Text("7\n")
        }

        Button(onClick = {
            entradaNumero += "8"
        }, modifier = Modifier.offset(x = maxWidth * 0.65f, y = maxHeight * 0.6f),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Gray,
                contentColor = Color.White
            )
        ) {
            Text("8\n")
        }

        Button(onClick = {
            entradaNumero += "9"
        }, modifier = Modifier.offset(x = maxWidth * 0.8f, y = maxHeight * 0.6f),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Gray,
                contentColor = Color.White
            )
        ) {
            Text("9\n")
        }

        Button(onClick = {
            entradaNumero += "0"
        }, modifier = Modifier.offset(x = maxWidth * 0.5f, y = maxHeight * 0.7f),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Gray,
                contentColor = Color.White
            )
        ) {
            Text("0\n")
        }

        // ================================================
        // Manipulação de entrada

        Button(onClick = {
            entradaNumero = ""
        }, modifier = Modifier.offset(x = maxWidth * 0.65f, y = maxHeight * 0.7f),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Red,
                contentColor = Color.White
            )
        ) {
            Text("C\n")
        }

        Button(onClick = {

            if (entradaNumero.takeLast(2) == ".0") {
                var stringTemporaria = entradaNumero.dropLast(2)
                entradaNumero = stringTemporaria
            } else {
                var stringTemporaria = entradaNumero.dropLast(1)
                entradaNumero = stringTemporaria
            }
        }, modifier = Modifier.offset(x = maxWidth * 0.8f, y = maxHeight * 0.7f),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Red,
                contentColor = Color.White
            )
        ) {
            Text("<-\n")
        }

        Button(onClick = {
            // Base primeiro, expoente depois: base ^ expoente.
            if (entradaNumero.trimEnd().lastOrNull()?.let { it.isDigit() || it == '.' } == true) {
                entradaNumero = entradaNumero.trimEnd() + " ^ "
            }
        }, modifier = Modifier.offset(x = maxWidth * 0.1f, y = maxHeight * 0.8f),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Gray,
                contentColor = Color.White
            ),
            border = BorderStroke(width = 2.dp, Color.Black)
        ) {
            Text("^\n")
        }

        Button(onClick = {
            // Índice primeiro, radicando depois: índice √ radicando.
            if (entradaNumero.trimEnd().lastOrNull()?.let { it.isDigit() || it == '.' } == true) {
                entradaNumero = entradaNumero.trimEnd() + " √ "
            }
        }, modifier = Modifier.offset(x = maxWidth * 0.29f, y = maxHeight * 0.8f),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Gray,
                contentColor = Color.White
            ),
            border = BorderStroke(width = 2.dp, Color.Black)
        ){
            Text("√\n")
        }

        Button(onClick = {
            // A expressão é sempre: base log logaritmando.
            if (entradaNumero.trimEnd().lastOrNull()?.let { it.isDigit() || it == '.' } == true) {
                entradaNumero = entradaNumero.trimEnd() + " log "
            }
        }, modifier = Modifier.offset(x = maxWidth * 0.455f, y = maxHeight * 0.8f),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Gray,
                contentColor = Color.White
            ),
            border = BorderStroke(width = 2.dp, Color.Black)
        ){
            Text("log\n")
        }

        Button(onClick = {
            println("Resultado")
            entradaNumero = calculadoraCientifica(entradaNumero)
        }, modifier = Modifier.offset(x = maxWidth * 0.8f, y = maxHeight * 0.8f),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.DarkGray,
                contentColor = Color.White
            ),
            border = BorderStroke(width = 2.dp, Color.Black)
        ) {
            Text("=\n")
        }

        // Ponto decimal:
        //   Adiciona '.' ao número atual apenas se ele ainda não contém um ponto
        //   e o último caractere digitado é um dígito.
        Button(onClick = {
            val ultimoChar = entradaNumero.lastOrNull()
            // Segmento atual: tudo após o último espaço (separador de operador)
            val segmentoAtual = entradaNumero.trimEnd().substringAfterLast(' ')
            val jaTemPonto = '.' in segmentoAtual
            if (ultimoChar?.isDigit() == true && !jaTemPonto) {
                entradaNumero += "."
            }
        }, modifier = Modifier.offset(x = maxWidth * 0.64f, y = maxHeight * 0.8f),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Gray,
                contentColor = Color.White
            ),
            border = BorderStroke( width = 2.dp, Color.Black)
        ) {
            Text(".\n")
        }
    }
}
