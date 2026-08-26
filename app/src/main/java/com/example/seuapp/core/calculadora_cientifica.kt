package com.example.seuapp.core

import kotlin.math.ln
import kotlin.math.pow

/*
  Calcula expressões sem parênteses da calculadora científica.
  O resultado deve ser inserido nessa ordem: base ^ expoente,
  índice √ radicando e base log logaritmando.
 */

fun calculadoraCientifica(expressaoNumerica: String): String {
    val tokens = tokenizar(expressaoNumerica) ?: return "Erro"
    if (tokens.size < 3 || tokens.size % 2 == 0) return "Erro"

    val valores = tokens.toMutableList()

    // Potências são associativas à direita: 2 ^ 3 ^ 2 = 2 ^ (3 ^ 2).
    for (indice in valores.indices.reversed()) {
        if (valores[indice] == "^") {
            if (!reduzir(valores, indice) { base, expoente -> base.pow(expoente) }) return "Erro"
        }
    }

    // Raiz: índice √ radicando. Logaritmo: base log logaritmando.
    var indice = 1
    while (indice < valores.size) {
        val operador = valores[indice]
        val calculado = when (operador) {
            "√" -> reduzir(valores, indice) { grau, radicando ->
                when {
                    grau == 0.0 -> Double.NaN
                    radicando < 0.0 && grau % 2.0 != 0.0 -> Double.NaN
                    radicando < 0.0 -> -(-radicando).pow(1.0 / grau)
                    else -> radicando.pow(1.0 / grau)
                }
            }
            "log" -> reduzir(valores, indice) { base, logaritmando ->
                if (base <= 0.0 || base == 1.0 || logaritmando <= 0.0) Double.NaN
                else ln(logaritmando) / ln(base)
            }
            else -> {
                indice += 2
                true
            }
        }
        if (!calculado) return "Erro"
    }

    indice = 1
    while (indice < valores.size) {
        if (valores[indice] in setOf("×", "÷")) {
            if (!reduzir(valores, indice) { esquerda, direita ->
                    if (valores[indice] == "÷" && direita == 0.0) Double.NaN
                    else if (valores[indice] == "×") esquerda * direita else esquerda / direita
                }
            ) return if (valores.getOrNull(indice) == "÷") "Div/0" else "Erro"
        } else {
            indice += 2
        }
    }

    indice = 1
    while (indice < valores.size) {
        if (!reduzir(valores, indice) { esquerda, direita ->
                if (valores[indice] == "+") esquerda + direita else esquerda - direita
            }
        ) return "Erro"
    }

    return formatar(valores.singleOrNull()?.toDoubleOrNull() ?: return "Erro")
}

private fun tokenizar(expressao: String): List<String>? {
    val tokens = mutableListOf<String>()
    var indice = 0
    var esperaNumero = true

    while (indice < expressao.length) {
        when (val caractere = expressao[indice]) {
            ' ', '\t', '\n' -> indice++
            '+', '-' -> {
                if (esperaNumero) {
                    val inicio = indice++
                    while (indice < expressao.length && expressao[indice].isWhitespace()) indice++
                    val fim = lerNumero(expressao, indice)
                    if (fim == indice) return null
                    tokens += expressao.substring(inicio, fim).replace(Regex("\\s"), "")
                    indice = fim
                    esperaNumero = false
                } else {
                    tokens += caractere.toString()
                    indice++
                    esperaNumero = true
                }
            }
            '×', '÷', '^', '√' -> {
                if (esperaNumero) return null
                tokens += caractere.toString()
                indice++
                esperaNumero = true
            }
            'l' -> {
                if (esperaNumero || !expressao.startsWith("log", indice)) return null
                tokens += "log"
                indice += 3
                esperaNumero = true
            }
            else -> {
                if (!esperaNumero) return null
                val fim = lerNumero(expressao, indice)
                if (fim == indice) return null
                tokens += expressao.substring(indice, fim)
                indice = fim
                esperaNumero = false
            }
        }
    }
    return if (esperaNumero) null else tokens
}

private fun lerNumero(texto: String, inicio: Int): Int {
    var indice = inicio
    var temDigito = false
    var temPonto = false
    while (indice < texto.length) {
        when {
            texto[indice].isDigit() -> temDigito = true
            texto[indice] == '.' && !temPonto -> temPonto = true
            else -> break
        }
        indice++
    }
    return if (temDigito) indice else inicio
}

private fun reduzir(
    valores: MutableList<String>,
    indiceOperador: Int,
    operacao: (Double, Double) -> Double
): Boolean {
    val esquerda = valores.getOrNull(indiceOperador - 1)?.toDoubleOrNull() ?: return false
    val direita = valores.getOrNull(indiceOperador + 1)?.toDoubleOrNull() ?: return false
    val resultado = operacao(esquerda, direita)
    if (!resultado.isFinite()) return false
    valores[indiceOperador - 1] = resultado.toString()
    valores.removeAt(indiceOperador)
    valores.removeAt(indiceOperador)
    return true
}

private fun formatar(resultado: Double): String = when {
    resultado == 0.0 -> "0"
    resultado == resultado.toLong().toDouble() -> resultado.toLong().toString()
    else -> resultado.toString()
}
