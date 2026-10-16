package com.example.pw_01_2.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.pw_01_2.data.BiogasCalculator

@Composable
fun BiogasCalculatorScreen() {
    var mass by remember { mutableStateOf("") }
    var dryMatter by remember { mutableStateOf("") }
    var biogasYield by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    val calculator = BiogasCalculator()

    val allFieldsFilled = mass.isNotBlank() && dryMatter.isNotBlank() && biogasYield.isNotBlank()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Біогазова установка",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary
        )

        OutlinedTextField(
            value = mass,
            onValueChange = { mass = it },
            label = { Text("Маса сировини (кг)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = dryMatter,
            onValueChange = { dryMatter = it },
            label = { Text("Вміст сухої речовини (%)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = biogasYield,
            onValueChange = { biogasYield = it },
            label = { Text("Вихід біогазу (м³/кг сухої речовини)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                val volume = calculator.calculateBiogasVolume(mass, dryMatter, biogasYield)
                result = calculator.formatResult(volume)
            },
            enabled = allFieldsFilled
        ) {
            Text("Розрахувати")
        }

        if (result.isNotEmpty()) {
            Text(
                text = "Обсяг біогазу: $result м³",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}