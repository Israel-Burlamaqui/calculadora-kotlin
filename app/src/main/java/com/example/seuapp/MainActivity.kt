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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

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
                modifier = Modifier.size(x, y),
                color = Color.White
            )
        }

        // Soma / Sinal positivo:
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

        // Diferença / Sinal negativo:
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

fun calcularResultado(expressaoNumeros: String): String
{
    // Normaliza espaços ao redor dos operadores (ex: "2 + 3" → "2+3")
    val expressaoNormalizada = expressaoNumeros.replace(" ", "")

    // Tokeniza a expressão separando números (com sinal e ponto decimal) e operadores.
    // Regras de sinal:
    //   '-' no início ou após um operador (+, -, ×, ÷) → sinal negativo do número
    //   '+' no início ou após um operador               → sinal positivo (ignorado, é o padrão)
    //   '+' ou '-' após um dígito ou '.'               → operador de adição/subtração
    val tokens = mutableListOf<String>()
    var numeroAtual = StringBuilder()

    for ((indice, caractere) in expressaoNormalizada.withIndex()) {
        val charAnterior = if (indice > 0) expressaoNormalizada[indice - 1] else null
        val aposOperador = charAnterior == null || charAnterior in listOf('+', '-', '×', '÷')

        if (caractere in listOf('+', '-', '×', '÷')) {
            when {
                // '-' como sinal negativo: início ou logo após operador
                caractere == '-' && aposOperador -> numeroAtual.append(caractere)

                // '+' como sinal positivo: início ou logo após operador — ignorado
                caractere == '+' && aposOperador -> { /* positivo é o padrão, não faz nada */ }

                // Operador real entre dois operandos
                else -> {
                    if (numeroAtual.isNotEmpty()) {
                        tokens.add(numeroAtual.toString())
                        numeroAtual = StringBuilder()
                    }
                    tokens.add(caractere.toString())
                }
            }
        } else {
            // Dígito ou ponto decimal: acumula no número atual
            numeroAtual.append(caractere)
        }
    }
    if (numeroAtual.isNotEmpty()) tokens.add(numeroAtual.toString())

    // Proteção: expressão inválida ou vazia
    if (tokens.isEmpty() || tokens.size < 3) return "Erro"

    // Passo 1 — Resolve ×÷ primeiro (maior precedência), da esquerda para direita
    val pilha = tokens.toMutableList()
    var i = 1
    while (i < pilha.size) {
        val operador = pilha[i]
        if (operador == "×" || operador == "÷") {
            val esquerda = pilha[i - 1].toDoubleOrNull() ?: return "Erro"
            val direita  = pilha[i + 1].toDoubleOrNull() ?: return "Erro"
            val parcial  = when {
                operador == "×"    -> esquerda * direita
                direita != 0.0     -> esquerda / direita
                else               -> return "Div/0"
            }
            pilha[i - 1] = parcial.toString()
            pilha.removeAt(i)  // remove operador
            pilha.removeAt(i)  // remove operando direito
        } else {
            i += 2
        }
    }

    // Passo 2 — Resolve +− (menor precedência), da esquerda para direita
    var resultado = pilha[0].toDoubleOrNull() ?: return "Erro"
    var j = 1
    while (j < pilha.size) {
        val operador = pilha[j]
        val proximo  = pilha[j + 1].toDoubleOrNull() ?: return "Erro"
        resultado = when (operador) {
            "+" -> resultado + proximo
            "-" -> resultado - proximo
            else -> return "Erro"
        }
        j += 2
    }

    // Remove o ".0" final se o resultado for inteiro (ex: 6.0 → "6")
    return if (resultado == resultado.toLong().toDouble())
        resultado.toLong().toString()
    else
        resultado.toString()
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