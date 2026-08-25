package com.example.seuapp.core

fun calcularResultado(expressaoNumeros: String): String
{
    // Normaliza espaços ao redor dos operadores (ex: "2 + 3" → "2+3")
    val expressaoNormalizada = expressaoNumeros.replace(" ", "")

    /*
        Transforma a expressão em tokens, separando números (com sinal e ponto decimal) e operadores.
        Regras de sinal:
       '-' no início ou após um operador (+, -, ×, ÷) → sinal negativo do número
       '+' no início ou após um operador               → sinal positivo (ignorado, é o padrão)
       '+' ou '-' após um dígito ou '.'               → operador de adição/subtração
     */
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
