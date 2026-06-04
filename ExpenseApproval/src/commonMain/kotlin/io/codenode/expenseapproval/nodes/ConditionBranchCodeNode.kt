/*
 * ConditionBranchCodeNode - if/else branch: routes ExpenseAmount to
 * the approved output when amount <= threshold, otherwise to escalated.
 * License: Apache 2.0
 */

package io.codenode.expenseapproval.nodes

import io.codenode.expenseapproval.iptypes.ExpenseAmount
import io.codenode.fbpdsl.model.CodeNodeFactory
import io.codenode.fbpdsl.model.CodeNodeType
import io.codenode.fbpdsl.runtime.CodeNodeDefinition
import io.codenode.fbpdsl.runtime.NodeRuntime
import io.codenode.fbpdsl.runtime.PortSpec
import io.codenode.fbpdsl.runtime.ProcessResult2

/**
 * Transformer node with 1 input and 2 outputs that routes the incoming
 * ExpenseAmount selectively:
 *   - `approved` output (port 1) when amount <= AUTO_APPROVE_THRESHOLD
 *   - `escalated` output (port 2) when amount >  AUTO_APPROVE_THRESHOLD
 *
 * This is the visual centerpiece of the demo — the if/else fork that
 * "lights up" the left or right path depending on the input value.
 */
object ConditionBranchCodeNode : CodeNodeDefinition {
    override val name = "ConditionBranch"
    override val category = CodeNodeType.TRANSFORMER
    override val description = "Routes ExpenseAmount to approved or escalated path by threshold"
    override val inputPorts = listOf(PortSpec("amount", ExpenseAmount::class))
    override val outputPorts = listOf(
        PortSpec("approved", ExpenseAmount::class),
        PortSpec("escalated", ExpenseAmount::class)
    )

    private const val AUTO_APPROVE_THRESHOLD = 100.0

    override fun createRuntime(name: String): NodeRuntime {
        return CodeNodeFactory.createIn1Out2Processor<ExpenseAmount, ExpenseAmount, ExpenseAmount>(
            name = name,
            process = { input ->
                if (input.amount <= AUTO_APPROVE_THRESHOLD) {
                    ProcessResult2.first(input)
                } else {
                    ProcessResult2.second(input)
                }
            }
        )
    }
}
