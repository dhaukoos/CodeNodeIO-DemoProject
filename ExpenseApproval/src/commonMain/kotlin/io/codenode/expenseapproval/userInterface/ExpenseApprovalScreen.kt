/*
 * ExpenseApprovalScreen - Expense Approval UI composable (presentation only)
 * License: Apache 2.0
 */

package io.codenode.expenseapproval.userInterface

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.codenode.expenseapproval.viewmodel.Approver
import io.codenode.expenseapproval.viewmodel.ExpenseApprovalEvent
import io.codenode.expenseapproval.viewmodel.ExpenseApprovalState
import io.codenode.expenseapproval.viewmodel.ExpenseApprovalStatus

/**
 * MVI Screen: pure two-parameter Composable. Renders four functional regions —
 * Amount Input, Submit Button, Status Badge, Approver Card — from the supplied
 * state snapshot. Emits user intents via [onEvent]. Holds no semantic state.
 */
@Composable
fun ExpenseApprovalScreen(
    state: ExpenseApprovalState,
    onEvent: (ExpenseApprovalEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    var localAmount by remember(state.amount) { mutableStateOf(state.amount) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        AmountInput(
            value = localAmount,
            onValueChange = { raw ->
                val sanitized = sanitizeAmount(raw)
                localAmount = sanitized
                onEvent(ExpenseApprovalEvent.AmountChanged(sanitized))
            }
        )

        SubmitButton(
            enabled = localAmount.isNotBlank() && state.status != ExpenseApprovalStatus.PENDING,
            isPending = state.status == ExpenseApprovalStatus.PENDING,
            onClick = { onEvent(ExpenseApprovalEvent.SubmitClicked) }
        )

        StatusBadge(status = state.status)

        ApproverCardSlot(status = state.status, approver = state.approver)
    }
}

@Composable
private fun AmountInput(
    value: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text("Amount") },
        placeholder = { Text("$0.00") },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
private fun SubmitButton(
    enabled: Boolean,
    isPending: Boolean,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = Modifier.fillMaxWidth()
    ) {
        if (isPending) {
            CircularProgressIndicator(
                modifier = Modifier.size(18.dp),
                color = MaterialTheme.colors.onPrimary,
                strokeWidth = 2.dp
            )
            Spacer(modifier = Modifier.width(8.dp))
        }
        Text("Submit Expense")
    }
}

@Composable
private fun StatusBadge(status: ExpenseApprovalStatus) {
    val (background, foreground, label) = when (status) {
        ExpenseApprovalStatus.IDLE -> Triple(
            MaterialTheme.colors.surface,
            MaterialTheme.colors.onSurface,
            "Idle"
        )
        ExpenseApprovalStatus.PENDING -> Triple(
            MaterialTheme.colors.primary.copy(alpha = 0.15f),
            MaterialTheme.colors.primary,
            "Pending"
        )
        ExpenseApprovalStatus.APPROVED -> Triple(
            MaterialTheme.colors.secondary,
            MaterialTheme.colors.onSecondary,
            "Approved"
        )
        ExpenseApprovalStatus.ESCALATED -> Triple(
            MaterialTheme.colors.error,
            MaterialTheme.colors.onError,
            "Escalated"
        )
    }

    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .background(background)
                .border(
                    width = 1.dp,
                    color = if (status == ExpenseApprovalStatus.IDLE)
                        MaterialTheme.colors.onSurface.copy(alpha = 0.12f)
                    else background,
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(horizontal = 12.dp, vertical = 6.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                when (status) {
                    ExpenseApprovalStatus.APPROVED -> {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = null,
                            tint = foreground,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                    }
                    ExpenseApprovalStatus.ESCALATED -> {
                        Icon(
                            imageVector = Icons.Default.Warning,
                            contentDescription = null,
                            tint = foreground,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                    }
                    else -> {}
                }
                Text(
                    text = label,
                    color = foreground,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

@Composable
private fun ApproverCardSlot(
    status: ExpenseApprovalStatus,
    approver: Approver?
) {
    when (status) {
        ExpenseApprovalStatus.APPROVED -> {
            Text(
                text = "Auto-approved — no manager review",
                color = MaterialTheme.colors.secondary,
                fontSize = 14.sp,
                fontStyle = FontStyle.Italic
            )
        }
        ExpenseApprovalStatus.ESCALATED -> {
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = 2.dp
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    if (approver == null) {
                        Text(
                            text = "Pending approver assignment",
                            color = MaterialTheme.colors.onSurface.copy(alpha = 0.6f),
                            fontSize = 14.sp,
                            fontStyle = FontStyle.Italic
                        )
                    } else {
                        Text(
                            text = approver.name,
                            color = MaterialTheme.colors.onSurface,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                        if (approver.role != null) {
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = approver.role,
                                color = MaterialTheme.colors.onSurface.copy(alpha = 0.6f),
                                fontSize = 13.sp
                            )
                        }
                    }
                }
            }
        }
        else -> {
            Spacer(modifier = Modifier.height(0.dp))
        }
    }
}

private fun sanitizeAmount(raw: String): String {
    val sb = StringBuilder()
    var seenDecimal = false
    for (ch in raw) {
        when {
            ch.isDigit() -> sb.append(ch)
            ch == '.' && !seenDecimal -> {
                sb.append(ch)
                seenDecimal = true
            }
        }
    }
    return sb.toString()
}
