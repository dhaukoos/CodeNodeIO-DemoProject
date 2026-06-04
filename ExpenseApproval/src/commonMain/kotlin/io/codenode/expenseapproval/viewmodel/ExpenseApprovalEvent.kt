/*
 * ExpenseApprovalEvent - User intents emitted by the Expense Approval screen
 * License: Apache 2.0
 */

package io.codenode.expenseapproval.viewmodel

sealed interface ExpenseApprovalEvent {
    data class AmountChanged(val value: String) : ExpenseApprovalEvent
    data object SubmitClicked : ExpenseApprovalEvent
}
