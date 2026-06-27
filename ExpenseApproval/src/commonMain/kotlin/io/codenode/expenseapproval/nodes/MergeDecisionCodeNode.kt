package io.codenode.expenseapproval.nodes

import io.codenode.fbpdsl.model.CodeNodeFactory
import io.codenode.fbpdsl.model.CodeNodeType
import io.codenode.fbpdsl.runtime.*
import kotlin.reflect.KClass

object MergeDecisionCodeNode : CodeNodeDefinition {
    override val name = "MergeDecision"
    override val category = CodeNodeType.MERGER
    override val description = "Merger node with 2 input(s) and 1 output(s)"
    override val inputPorts = listOf(PortSpec("input1", Any::class), PortSpec("input2", Any::class))
    override val outputPorts = listOf(PortSpec("output1", Any::class))

    // ConditionBranch routes IP to ONE output (either approved → Approve or
    // escalated → Escalate), so MergeDecision receives on ONE input per IP.
    // Must use the "Any" variant — `createIn2Out1Processor` does synchronous
    // AND-receive (waits for both inputs) and deadlocks selective-emit
    // pipelines. See memory note `project_sinkin_n_and_deadlock.md` —
    // same gotcha bites the corresponding non-sink multi-input runtimes.
    // Sentinel for the "other" cached input — In2AnyOut1Runtime feeds BOTH
    // cached values to the process lambda when EITHER input fires. We pick
    // the one that just fired (the non-sentinel) and forward it; the sentinel
    // is never observed downstream because the alternative input only fires
    // exclusively (selective routing from ConditionBranch).
    private val sentinel: Any = Unit

    override fun createRuntime(name: String): NodeRuntime {
        return CodeNodeFactory.createIn2AnyOut1Processor<Any, Any, Any>(
            name = name,
            initialValue1 = sentinel,
            initialValue2 = sentinel,
            process = { input1, input2 ->
                // Forward whichever input is NOT the sentinel — exclusive routing
                // means at most one of the two carries a real ApprovalDecision per IP.
                if (input1 !== sentinel) input1 else input2
            },
        )
    }
}

