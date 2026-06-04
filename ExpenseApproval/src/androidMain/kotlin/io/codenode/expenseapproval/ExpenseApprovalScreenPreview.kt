/*
 * ExpenseApprovalScreenPreview - Android Studio @Preview for the Expense Approval screen
 * License: Apache 2.0
 */

package io.codenode.expenseapproval

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import io.codenode.expenseapproval.userInterface.ExpenseApprovalScreen
import io.codenode.expenseapproval.viewmodel.Approver
import io.codenode.expenseapproval.viewmodel.ExpenseApprovalState
import io.codenode.expenseapproval.viewmodel.ExpenseApprovalStatus

@Preview(showBackground = true)
@Composable
private fun ExpenseApprovalScreenIdlePreview() {
    MaterialTheme {
        Surface {
            ExpenseApprovalScreen(
                state = ExpenseApprovalState(),
                onEvent = {}
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ExpenseApprovalScreenPendingPreview() {
    MaterialTheme {
        Surface {
            ExpenseApprovalScreen(
                state = ExpenseApprovalState(
                    amount = "250.00",
                    status = ExpenseApprovalStatus.PENDING
                ),
                onEvent = {}
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ExpenseApprovalScreenApprovedPreview() {
    MaterialTheme {
        Surface {
            ExpenseApprovalScreen(
                state = ExpenseApprovalState(
                    amount = "75.00",
                    status = ExpenseApprovalStatus.APPROVED
                ),
                onEvent = {}
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ExpenseApprovalScreenEscalatedPreview() {
    MaterialTheme {
        Surface {
            ExpenseApprovalScreen(
                state = ExpenseApprovalState(
                    amount = "1250.00",
                    status = ExpenseApprovalStatus.ESCALATED,
                    approver = Approver(name = "Lina Park", role = "Director, Finance")
                ),
                onEvent = {}
            )
        }
    }
}
