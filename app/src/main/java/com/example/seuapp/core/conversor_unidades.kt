package com.example.seuapp.core

fun converterTemperatura(nomeUnidadeTemperatura: String, valorTemperatura: Int): Int
{
    var resultado: Float = 0.0f
    if (nomeUnidadeTemperatura == "Celsius")
    {
        resultado = (((valorTemperatura * 1.8f) + 32)).toFloat()
    } else if (nomeUnidadeTemperatura == "Fahrenheit")
    {
        resultado = ((valorTemperatura - 32) * (5 / 9)).toFloat()
    }

    return resultado.toInt()
}