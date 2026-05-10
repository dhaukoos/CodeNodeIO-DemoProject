/*
 * Greeter — minimal Design B-shaped Screen for feature 087 / US3
 * Demonstrates the canonical MVI signature: (state, onEvent, modifier).
 * License: Apache 2.0
 */

package io.codenode.greeter.userInterface

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.codenode.greeter.viewmodel.GreeterEvent
import io.codenode.greeter.viewmodel.GreeterState

@Composable
fun Greeter(
    state: GreeterState,
    onEvent: (GreeterEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    var nameText by remember { mutableStateOf("") }

    Column(
        modifier = modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Input", fontWeight = FontWeight.Bold, fontSize = 18.sp)

        OutlinedTextField(
            value = nameText,
            onValueChange = { nameText = it },
            label = { Text("Name") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = { onEvent(GreeterEvent.UpdateName(nameText)) },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Greet")
        }

        Divider()

        Text("Output", fontWeight = FontWeight.Bold, fontSize = 18.sp)
        Text(text = state.greeting.ifEmpty { "(no greeting yet)" })
        Text(text = "Name length: ${state.nameLength}")
    }
}
