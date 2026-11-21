package com.example.seuapp.core.entrada

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.seuapp.core.calcularResultado
import com.example.seuapp.core.calculadoraCientifica

@Composable
fun BoxWithConstraintsScope.BotoesCalculadoraSimples(
    entrada: String,
    onEntradaChange: (String) -> Unit,
    navController: NavController
) {
    // Soma / Sinal positivo
    Button(
        onClick = {
            val ultimoChar = entrada.trimEnd().lastOrNull()
            val novaEntrada = when {
                ultimoChar == null || ultimoChar in listOf('+', '×', '÷') -> entrada
                ultimoChar == '-' -> entrada.trimEnd().dropLast(1)
                else -> entrada + " + "
            }
            onEntradaChange(novaEntrada)
        },
        modifier = Modifier.offset(x = maxWidth * 0.35f, y = maxHeight * 0.4f),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.DarkGray,
            contentColor = Color.White
        ),
        border = BorderStroke(width = 2.dp, Color.Black)
    ) {
        Text("+\n")
    }

    // Subtração / Sinal negativo
    Button(
        onClick = {
            val expressaoTrim = entrada.trimEnd()
            val ultimoChar = expressaoTrim.lastOrNull()
            val novaEntrada = when {
                ultimoChar == null || ultimoChar in listOf('×', '÷') -> entrada.trimEnd() + "-"
                ultimoChar == '+' -> expressaoTrim.dropLast(1) + "-"
                ultimoChar == '-' -> expressaoTrim.dropLast(1)
                else -> entrada + " - "
            }
            onEntradaChange(novaEntrada)
        },
        modifier = Modifier.offset(x = maxWidth * 0.35f, y = maxHeight * 0.5f),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.DarkGray,
            contentColor = Color.White
        ),
        border = BorderStroke(width = 2.dp, Color.Black)
    ) {
        Text("-\n")
    }

    // Produto
    Button(
        onClick = {
            println("Produto")
            onEntradaChange(entrada + " × ")
        },
        modifier = Modifier.offset(x = maxWidth * 0.35f, y = maxHeight * 0.6f),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.DarkGray,
            contentColor = Color.White
        ),
        border = BorderStroke(width = 2.dp, Color.Black)
    ) {
        Text("×\n")
    }

    // Razão
    Button(
        onClick = {
            println("Razão")
            onEntradaChange(entrada + " ÷ ")
        },
        modifier = Modifier.offset(x = maxWidth * 0.35f, y = maxHeight * 0.7f),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.DarkGray,
            contentColor = Color.White
        ),
        border = BorderStroke(width = 2.dp, Color.Black)
    ) {
        Text("÷\n")
    }

    // Voltar
    Button(
        onClick = {
            navController.navigate("principal")
        },
        modifier = Modifier.offset(x = maxWidth * 0.38f, y = maxHeight * 0.9f)
    ) {
        Text("Voltar")
    }

    // Números 1..9, 0
    val numeros = listOf(
        Pair("1", Pair(0.5f, 0.4f)),
        Pair("2", Pair(0.65f, 0.4f)),
        Pair("3", Pair(0.8f, 0.4f)),
        Pair("4", Pair(0.5f, 0.5f)),
        Pair("5", Pair(0.65f, 0.5f)),
        Pair("6", Pair(0.8f, 0.5f)),
        Pair("7", Pair(0.5f, 0.6f)),
        Pair("8", Pair(0.65f, 0.6f)),
        Pair("9", Pair(0.8f, 0.6f)),
        Pair("0", Pair(0.5f, 0.7f))
    )

    for ((digito, pos) in numeros) {
        Button(
            onClick = {
                onEntradaChange(entrada + digito)
            },
            modifier = Modifier.offset(x = maxWidth * pos.first, y = maxHeight * pos.second),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Gray,
                contentColor = Color.White
            ),
            border = BorderStroke(width = 2.dp, Color.DarkGray)
        ) {
            Text("$digito\n")
        }
    }

    // Limpar (C)
    Button(
        onClick = {
            onEntradaChange("")
        },
        modifier = Modifier.offset(x = maxWidth * 0.65f, y = maxHeight * 0.7f),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Red,
            contentColor = Color.White
        ),
        border = BorderStroke(width = 2.dp, Color.Black)
    ) {
        Text("C\n")
    }

    // Backspace (<-)
    Button(
        onClick = {
            val novaEntrada = if (entrada.takeLast(2) == ".0") {
                entrada.dropLast(2)
            } else {
                entrada.dropLast(1)
            }
            onEntradaChange(novaEntrada)
        },
        modifier = Modifier.offset(x = maxWidth * 0.8f, y = maxHeight * 0.7f),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Red,
            contentColor = Color.White
        ),
        border = BorderStroke(width = 2.dp, Color.Black)
    ) {
        Text("<-\n")
    }

    // Resultado (=)
    Button(
        onClick = {
            onEntradaChange(calcularResultado(entrada))
        },
        modifier = Modifier.offset(x = maxWidth * 0.8f, y = maxHeight * 0.8f),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.DarkGray,
            contentColor = Color.White
        ),
        border = BorderStroke(width = 2.dp, Color.Black)
    ) {
        Text("=\n")
    }

    // Ponto (.)
    Button(
        onClick = {
            val ultimoChar = entrada.lastOrNull()
            val segmentoAtual = entrada.trimEnd().substringAfterLast(' ')
            val jaTemPonto = '.' in segmentoAtual
            if (ultimoChar?.isDigit() == true && !jaTemPonto) {
                onEntradaChange(entrada + ".")
            }
        },
        modifier = Modifier.offset(x = maxWidth * 0.65f, y = maxHeight * 0.8f),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.DarkGray,
            contentColor = Color.White
        ),
        border = BorderStroke(width = 2.dp, Color.Black)
    ) {
        Text(".\n")
    }
}

@Composable
fun BoxWithConstraintsScope.BotoesCalculadoraCientifica(
    entrada: String,
    onEntradaChange: (String) -> Unit,
    navController: NavController
) {
    // Soma / Sinal positivo
    Button(
        onClick = {
            val ultimoChar = entrada.trimEnd().lastOrNull()
            val novaEntrada = when {
                ultimoChar == null || ultimoChar in listOf('+', '×', '÷') -> entrada
                ultimoChar == '-' -> entrada.trimEnd().dropLast(1)
                else -> entrada + " + "
            }
            onEntradaChange(novaEntrada)
        },
        modifier = Modifier.offset(x = maxWidth * 0.35f, y = maxHeight * 0.4f),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.DarkGray,
            contentColor = Color.White
        ),
        border = BorderStroke(width = 2.dp, Color.Black)
    ) {
        Text("+\n")
    }

    // Subtração / Sinal negativo
    Button(
        onClick = {
            val expressaoTrim = entrada.trimEnd()
            val ultimoChar = expressaoTrim.lastOrNull()
            val novaEntrada = when {
                ultimoChar == null || ultimoChar in listOf('×', '÷') -> entrada.trimEnd() + "-"
                ultimoChar == '+' -> expressaoTrim.dropLast(1) + "-"
                ultimoChar == '-' -> expressaoTrim.dropLast(1)
                else -> entrada + " - "
            }
            onEntradaChange(novaEntrada)
        },
        modifier = Modifier.offset(x = maxWidth * 0.35f, y = maxHeight * 0.5f),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.DarkGray,
            contentColor = Color.White
        ),
        border = BorderStroke(width = 2.dp, Color.Black)
    ) {
        Text("-\n")
    }

    // Produto
    Button(
        onClick = {
            println("Produto")
            onEntradaChange(entrada + " × ")
        },
        modifier = Modifier.offset(x = maxWidth * 0.35f, y = maxHeight * 0.6f),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.DarkGray,
            contentColor = Color.White
        ),
        border = BorderStroke(width = 2.dp, Color.Black)
    ) {
        Text("×\n")
    }

    // Razão
    Button(
        onClick = {
            println("Razão")
            onEntradaChange(entrada + " ÷ ")
        },
        modifier = Modifier.offset(x = maxWidth * 0.35f, y = maxHeight * 0.7f),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.DarkGray,
            contentColor = Color.White
        ),
        border = BorderStroke(width = 2.dp, Color.Black)
    ) {
        Text("÷\n")
    }

    // Voltar
    Button(
        onClick = {
            navController.navigate("principal")
        },
        modifier = Modifier.offset(x = maxWidth * 0.38f, y = maxHeight * 0.9f)
    ) {
        Text("Voltar")
    }

    // Números 1..9, 0
    val numeros = listOf(
        Pair("1", Pair(0.5f, 0.4f)),
        Pair("2", Pair(0.65f, 0.4f)),
        Pair("3", Pair(0.8f, 0.4f)),
        Pair("4", Pair(0.5f, 0.5f)),
        Pair("5", Pair(0.65f, 0.5f)),
        Pair("6", Pair(0.8f, 0.5f)),
        Pair("7", Pair(0.5f, 0.6f)),
        Pair("8", Pair(0.65f, 0.6f)),
        Pair("9", Pair(0.8f, 0.6f)),
        Pair("0", Pair(0.5f, 0.7f))
    )

    for ((digito, pos) in numeros) {
        Button(
            onClick = {
                onEntradaChange(entrada + digito)
            },
            modifier = Modifier.offset(x = maxWidth * pos.first, y = maxHeight * pos.second),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Gray,
                contentColor = Color.White
            )
        ) {
            Text("$digito\n")
        }
    }

    // Limpar (C)
    Button(
        onClick = {
            onEntradaChange("")
        },
        modifier = Modifier.offset(x = maxWidth * 0.65f, y = maxHeight * 0.7f),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Red,
            contentColor = Color.White
        )
    ) {
        Text("C\n")
    }

    // Backspace (<-)
    Button(
        onClick = {
            val novaEntrada = if (entrada.takeLast(2) == ".0") {
                entrada.dropLast(2)
            } else {
                entrada.dropLast(1)
            }
            onEntradaChange(novaEntrada)
        },
        modifier = Modifier.offset(x = maxWidth * 0.8f, y = maxHeight * 0.7f),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Red,
            contentColor = Color.White
        )
    ) {
        Text("<-\n")
    }

    // Potência (^)
    Button(
        onClick = {
            if (entrada.trimEnd().lastOrNull()?.let { it.isDigit() || it == '.' } == true) {
                onEntradaChange(entrada.trimEnd() + " ^ ")
            }
        },
        modifier = Modifier.offset(x = maxWidth * 0.1f, y = maxHeight * 0.8f),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Gray,
            contentColor = Color.White
        ),
        border = BorderStroke(width = 2.dp, Color.Black)
    ) {
        Text("^\n")
    }

    // Raiz (√)
    Button(
        onClick = {
            if (entrada.trimEnd().lastOrNull()?.let { it.isDigit() || it == '.' } == true) {
                onEntradaChange(entrada.trimEnd() + " √ ")
            }
        },
        modifier = Modifier.offset(x = maxWidth * 0.29f, y = maxHeight * 0.8f),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Gray,
            contentColor = Color.White
        ),
        border = BorderStroke(width = 2.dp, Color.Black)
    ){
        Text("√\n")
    }

    // Logaritmo (log)
    Button(
        onClick = {
            if (entrada.trimEnd().lastOrNull()?.let { it.isDigit() || it == '.' } == true) {
                onEntradaChange(entrada.trimEnd() + " log ")
            }
        },
        modifier = Modifier.offset(x = maxWidth * 0.455f, y = maxHeight * 0.8f),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Gray,
            contentColor = Color.White
        ),
        border = BorderStroke(width = 2.dp, Color.Black)
    ){
        Text("log\n")
    }

    // Resultado (=)
    Button(
        onClick = {
            onEntradaChange(calculadoraCientifica(entrada))
        },
        modifier = Modifier.offset(x = maxWidth * 0.8f, y = maxHeight * 0.8f),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.DarkGray,
            contentColor = Color.White
        ),
        border = BorderStroke(width = 2.dp, Color.Black)
    ) {
        Text("=\n")
    }

    // Ponto (.)
    Button(
        onClick = {
            val ultimoChar = entrada.lastOrNull()
            val segmentoAtual = entrada.trimEnd().substringAfterLast(' ')
            val jaTemPonto = '.' in segmentoAtual
            if (ultimoChar?.isDigit() == true && !jaTemPonto) {
                onEntradaChange(entrada + ".")
            }
        },
        modifier = Modifier.offset(x = maxWidth * 0.64f, y = maxHeight * 0.8f),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Gray,
            contentColor = Color.White
        ),
        border = BorderStroke( width = 2.dp, Color.Black)
    ) {
        Text(".\n")
    }
}
