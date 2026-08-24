package com.example.seuapp.telas

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
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
import com.example.seuapp.core.calcularResultado

@Composable
fun TelaCalculadora(navController: NavController)
{
    var entradaNumeroInteiro by remember { mutableStateOf("") }

    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {

        // Display
        Surface(color = Color.DarkGray,
            modifier = Modifier.offset(x = maxWidth * 0.1f, y = maxHeight * 0.1f)
            )
        {
            val x = maxWidth * 0.8f
            val y = maxHeight * 0.2f
            Text("$entradaNumeroInteiro",
                modifier = Modifier.size(x, y),
                color = Color.White
            )
        }

        // Soma / Sinal positivo (Gemini):
        //   - Após dígito ou '.': adiciona '+' como operador
        //   - Após '-' de sinal: remove o '-' (torna o número positivo)
        //   - Após outro operador ou expressão vazia: não faz nada (positivo é o padrão)
        Button(onClick = {
            val ultimoChar = entradaNumeroInteiro.trimEnd().lastOrNull()
            when {
                ultimoChar == null || ultimoChar in listOf('+', '×', '÷') -> { }
                ultimoChar == '-' ->
                    entradaNumeroInteiro = entradaNumeroInteiro.trimEnd().dropLast(1)
                else ->
                    entradaNumeroInteiro += " + "
            }
        }, modifier = Modifier.offset(x = maxWidth * 0.35f, y = maxHeight * 0.4f)
        ) {
            Text("+\n")
        }

        // Diferença / Sinal negativo (Gemini):
        //   - Expressão vazia ou após operador ×÷: adiciona '-' como sinal do próximo número
        //   - Após '+' de operador: substitui por '-'
        //   - Após '-' de sinal: remove (toggle → positivo)
        //   - Após dígito ou '.': adiciona '-' como operador
        Button(onClick = {
            val expressaoTrim = entradaNumeroInteiro.trimEnd()
            val ultimoChar = expressaoTrim.lastOrNull()
            when {
                ultimoChar == null || ultimoChar in listOf('×', '÷') ->
                    entradaNumeroInteiro = entradaNumeroInteiro.trimEnd() + "-"
                ultimoChar == '+' ->
                    entradaNumeroInteiro = expressaoTrim.dropLast(1) + "-"
                ultimoChar == '-' ->
                    entradaNumeroInteiro = expressaoTrim.dropLast(1)
                else ->
                    entradaNumeroInteiro += " - "
            }
        }, modifier = Modifier.offset(x = maxWidth * 0.35f, y = maxHeight * 0.5f)
        ) {
            Text("-\n")
        }

        // Produto
        Button(onClick = {
            println("Produto")
            entradaNumeroInteiro += " × "
        }, modifier = Modifier.offset(x = maxWidth * 0.35f, y = maxHeight * 0.6f)
        ) {
            Text("×\n")
        }

        // Razão
        Button(onClick = {
            println("Razão")
            entradaNumeroInteiro += " ÷ "
        }, modifier = Modifier.offset(x = maxWidth * 0.35f, y = maxHeight * 0.7f)
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
            entradaNumeroInteiro += "1"
        }, modifier = Modifier.offset(x = maxWidth * 0.5f, y = maxHeight * 0.4f)
        ) {
            Text("1\n")
        }

        Button(onClick = {
            entradaNumeroInteiro += "2"
        }, modifier = Modifier.offset(x = maxWidth * 0.65f, y = maxHeight * 0.4f)
        ) {
            Text("2\n")
        }

        Button(onClick =  {
            entradaNumeroInteiro += "3"
        }, modifier = Modifier.offset(x = maxWidth * 0.8f, y = maxHeight * 0.4f)
        ) {
            Text("3\n")
        }

        Button(onClick = {
            entradaNumeroInteiro += "4"
        }, modifier = Modifier.offset(x = maxWidth * 0.5f, y = maxHeight * 0.5f)
        ) {
            Text("4\n")
        }

        Button(onClick = {
            entradaNumeroInteiro += "5"
        }, modifier = Modifier.offset(x = maxWidth * 0.65f, y = maxHeight * 0.5f)
        ) {
            Text("5\n")
        }

        Button(onClick = {
            entradaNumeroInteiro += "6"
        }, modifier = Modifier.offset(x = maxWidth * 0.8f, y = maxHeight * 0.5f)
        ) {
            Text("6\n")
        }

        Button(onClick = {
            entradaNumeroInteiro += "7"
        }, modifier = Modifier.offset(x = maxWidth * 0.5f, y = maxHeight * 0.6f)
        ) {
            Text("7\n")
        }

        Button(onClick = {
            entradaNumeroInteiro += "8"
        }, modifier = Modifier.offset(x = maxWidth * 0.65f, y = maxHeight * 0.6f)
        ) {
            Text("8\n")
        }

        Button(onClick = {
            entradaNumeroInteiro += "9"
        }, modifier = Modifier.offset(x = maxWidth * 0.8f, y = maxHeight * 0.6f)
        ) {
            Text("9\n")
        }

        Button(onClick = {
            entradaNumeroInteiro += "0"
        }, modifier = Modifier.offset(x = maxWidth * 0.5f, y = maxHeight * 0.7f)
        ) {
            Text("0\n")
        }

        // ================================================
        // Manipulação de entrada

        Button(onClick = {
            entradaNumeroInteiro = ""
        }, modifier = Modifier.offset(x = maxWidth * 0.65f, y = maxHeight * 0.7f)
        ) {
            Text("C\n")
        }

        Button(onClick = {
            val tamanho = entradaNumeroInteiro.length
            if (entradaNumeroInteiro.takeLast(2) == ".0") {
                var stringTemporaria = entradaNumeroInteiro.dropLast(2)
                entradaNumeroInteiro = stringTemporaria
            } else {
                var stringTemporaria = entradaNumeroInteiro.dropLast(1)
                entradaNumeroInteiro = stringTemporaria
            }
        }, modifier = Modifier.offset(x = maxWidth * 0.8f, y = maxHeight * 0.7f)
        ) {
            Text("<-\n")
        }

        // Resultado
        Button(onClick = {
            entradaNumeroInteiro = calcularResultado(entradaNumeroInteiro)
        }, modifier = Modifier.offset(x = maxWidth * 0.8f, y = maxHeight * 0.8f)
        ) {
            Text("=\n")
        }

        // Ponto decimal:
        //   Adiciona '.' ao número atual apenas se ele ainda não contém um ponto
        //   e o último caractere digitado é um dígito.
        Button(onClick = {
            val ultimoChar = entradaNumeroInteiro.lastOrNull()
            // Segmento atual: tudo após o último espaço (separador de operador)
            val segmentoAtual = entradaNumeroInteiro.trimEnd().substringAfterLast(' ')
            val jaTemPonto = '.' in segmentoAtual
            if (ultimoChar?.isDigit() == true && !jaTemPonto) {
                entradaNumeroInteiro += "."
            }
        }, modifier = Modifier.offset(x = maxWidth * 0.65f, y = maxHeight * 0.8f)
        ) {
            Text(".\n")
        }
    }
}