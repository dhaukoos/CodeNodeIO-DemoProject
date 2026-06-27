package io.codenode.xycoordrepo.userInterface

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import io.codenode.xycoordrepo.iptypes.XYCoord

@Composable
fun AddUpdateXYCoord(
    existingItem: XYCoord? = null,
    onSave: (XYCoord) -> Unit,
    onCancel: () -> Unit,
    modifier: Modifier = Modifier
) {
    var XText by remember { mutableStateOf(existingItem?.X?.toString() ?: "") }
    var YText by remember { mutableStateOf(existingItem?.Y?.toString() ?: "") }

    val isUpdate = existingItem != null

    Column(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = if (isUpdate) "Update XYCoord" else "Add XYCoord",
            style = MaterialTheme.typography.titleMedium
        )

        OutlinedTextField(
            value = XText,
            onValueChange = { newValue ->
                if (newValue.isEmpty() || newValue.toDoubleOrNull() != null) {
                    XText = newValue
                }
            },
            label = { Text("X") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = YText,
            onValueChange = { newValue ->
                if (newValue.isEmpty() || newValue.toDoubleOrNull() != null) {
                    YText = newValue
                }
            },
            label = { Text("Y") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            modifier = Modifier.fillMaxWidth()
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(onClick = onCancel) {
                Text("Cancel")
            }
            Button(
                onClick = {
                    val item = XYCoord(
                        id = existingItem?.id ?: 0,
                        X = XText.toDoubleOrNull() ?: 0.0,
                        Y = YText.toDoubleOrNull() ?: 0.0
                    )
                    onSave(item)
                }
            ) {
                Text(if (isUpdate) "Update" else "Add")
            }
        }
    }
}
