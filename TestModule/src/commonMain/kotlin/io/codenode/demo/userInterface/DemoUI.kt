/*
 * DemoUI - Calculator UI with input validation and results display
 * License: Apache 2.0
 */

package io.codenode.demo.userInterface

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DemoUI(modifier: Modifier = Modifier) {
    var textA by remember { mutableStateOf("") }
    var textB by remember { mutableStateOf("") }
    var errorA by remember { mutableStateOf(false) }
    var errorB by remember { mutableStateOf(false) }
    var results by remember { mutableStateOf<CalculationResults?>(null) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        InputSection(
            textA = textA,
            textB = textB,
            errorA = errorA,
            errorB = errorB,
            onTextAChanged = { textA = it; errorA = false },
            onTextBChanged = { textB = it; errorB = false },
            onCalculate = {
                val a = textA.toDoubleOrNull()
                val b = textB.toDoubleOrNull()
                errorA = a == null
                errorB = b == null
                if (a != null && b != null) {
                    results = CalculationResults(
                        sum = a + b,
                        difference = a - b,
                        product = a * b,
                        quotient = if (b != 0.0) a / b else Double.NaN
                    )
                }
            }
        )

        Divider()

        ResultsSection(results = results)
    }
}

@Composable
private fun InputSection(
    textA: String,
    textB: String,
    errorA: Boolean,
    errorB: Boolean,
    onTextAChanged: (String) -> Unit,
    onTextBChanged: (String) -> Unit,
    onCalculate: () -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text("Input", fontWeight = FontWeight.Bold, fontSize = 18.sp)

        NumberField(label = "A", value = textA, isError = errorA, onValueChange = onTextAChanged)
        NumberField(label = "B", value = textB, isError = errorB, onValueChange = onTextBChanged)

        Button(
            onClick = onCalculate,
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Calculate")
        }
    }
}

@Composable
private fun NumberField(
    label: String,
    value: String,
    isError: Boolean,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        isError = isError,
        singleLine = true,
        trailingIcon = {
            if (isError) {
                Icon(
                    imageVector = Icons.Default.Warning,
                    contentDescription = "Invalid number",
                    tint = MaterialTheme.colors.error
                )
            }
        },
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
private fun ResultsSection(results: CalculationResults?) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text("Results", fontWeight = FontWeight.Bold, fontSize = 18.sp)

        if (results == null) {
            Text(
                "Enter values and press Calculate",
                color = Color.Gray,
                fontSize = 14.sp
            )
        } else {
            ResultRow("Sum (A + B)", results.sum)
            ResultRow("Difference (A - B)", results.difference)
            ResultRow("Product (A * B)", results.product)
            ResultRow(
                label = "Quotient (A / B)",
                value = results.quotient,
                errorText = if (results.quotient.isNaN()) "undefined (division by zero)" else null
            )
        }
    }
}

@Composable
private fun ResultRow(label: String, value: Double, errorText: String? = null) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(label, fontSize = 14.sp)
        if (errorText != null) {
            Text(errorText, color = MaterialTheme.colors.error, fontSize = 14.sp)
        } else {
            Text(formatNumber(value), fontWeight = FontWeight.Medium, fontSize = 14.sp)
        }
    }
}

private fun formatNumber(value: Double): String =
    if (value == value.toLong().toDouble()) value.toLong().toString()
    else "%.6g".format(value)

data class CalculationResults(
    val sum: Double,
    val difference: Double,
    val product: Double,
    val quotient: Double
)
