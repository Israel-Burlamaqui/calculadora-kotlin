package com.example.seuapp.core
import android.util.Log

fun converterTemperatura(nomeUnidadeTemperatura: String, valorTemperatura: Float): Int
{
    Log.d("Aviso", "Temperatura selecionada: $nomeUnidadeTemperatura\n")
    var resultado: Float = 0.0f
    if (nomeUnidadeTemperatura == "Fahrenheit")
    {
        resultado = (((valorTemperatura * 1.8f) + 32f))
    } else if (nomeUnidadeTemperatura == "Celsius")
    {
        resultado = ((valorTemperatura - 32f) * (5f / 9f))
    }
    else
    {
        resultado = 0f
    }

    Log.d("Aviso", "Resultado da conversão: $resultado\n")
    //print("Conversão do resultado funcionou: \nResultado: $resultado\n")
    return resultado.toInt()
}