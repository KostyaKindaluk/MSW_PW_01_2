package com.example.pw_01_2.data

import java.util.Locale

class BiogasCalculator {
    fun calculateBiogasVolume(mass: String, dryMatter: String, biogasYield: String): Double {
        val m = mass.replace(",", ".").toDoubleOrNull() ?: 0.0

        val dm = dryMatter.replace(",", ".").toDoubleOrNull()?.div(100) ?: 0.0

        val y = biogasYield.replace(",", ".").toDoubleOrNull() ?: 0.0

        //Обчислення обсягу біогазу: V = маса × частка сухої речовини × вихід біогазу
        return m * dm * y
    }

    fun formatResult(value: Double): String {
        return String.format(Locale.US, "%.2f", value)
    }
}