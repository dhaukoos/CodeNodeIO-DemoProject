/*
 * ApprovalDecision - Custom IP Type
 * @IPType
 * @TypeName ApprovalDecision
 * @TypeId ip_approvaldecision
 * @Color rgb(0, 150, 136)
 * License: Apache 2.0
 */

package io.codenode.expenseapproval.iptypes

import io.codenode.expenseapproval.viewmodel.ExpenseApprovalStatus

data class ApprovalDecision(
    val amount: Double,
    val status: ExpenseApprovalStatus,
    val approverName: String? = null,
    val approverRole: String? = null
)
