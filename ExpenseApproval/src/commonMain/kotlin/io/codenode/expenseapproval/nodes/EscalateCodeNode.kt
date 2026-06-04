/*
 * EscalateCodeNode - Manager-routed transformer: wraps an ExpenseAmount in
 * an ApprovalDecision with status = ESCALATED and a default approver.
 * License: Apache 2.0
 */

package io.codenode.expenseapproval.nodes

import io.codenode.expenseapproval.iptypes.ApprovalDecision
import io.codenode.expenseapproval.iptypes.ExpenseAmount
import io.codenode.expenseapproval.viewmodel.ExpenseApprovalStatus
import io.codenode.fbpdsl.model.CodeNodeFactory
import io.codenode.fbpdsl.model.CodeNodeType
import io.codenode.fbpdsl.runtime.CodeNodeDefinition
import io.codenode.fbpdsl.runtime.NodeRuntime
import io.codenode.fbpdsl.runtime.PortSpec

object EscalateCodeNode : CodeNodeDefinition {
    override val name = "Escalate"
    override val category = CodeNodeType.TRANSFORMER
    override val description = "Wraps the amount in an ESCALATED ApprovalDecision with approver Lina Park"
    override val inputPorts = listOf(PortSpec("amount", ExpenseAmount::class))
    override val outputPorts = listOf(PortSpec("decision", ApprovalDecision::class))

    override fun createRuntime(name: String): NodeRuntime {
        return CodeNodeFactory.createContinuousTransformer<ExpenseAmount, ApprovalDecision>(
            name = name,
            transform = { input ->
                ApprovalDecision(
                    amount = input.amount,
                    status = ExpenseApprovalStatus.ESCALATED,
                    approverName = "Lina Park",
                    approverRole = "Director, Finance"
                )
            }
        )
    }
}
