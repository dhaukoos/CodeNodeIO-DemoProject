/*
 * ExpenseApprovalState - UI state snapshot for the Expense Approval screen
 * License: Apache 2.0
 */

package io.codenode.expenseapproval.viewmodel

enum class ExpenseApprovalStatus {
    IDLE,
    PENDING,
    APPROVED,
    ESCALATED
}

data class Approver(
    val name: String,
    val role: String? = null
)

data class ExpenseApprovalState(
    val amount: String = "",
    val status: ExpenseApprovalStatus = ExpenseApprovalStatus.IDLE,
    val approver: Approver? = null
)
