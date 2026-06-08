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

    // TODO: Replace with your processing logic
    private val processBlock: In2Out1ProcessBlock<Any, Any, Any> = { input1, input2 -> input1 }

    override fun createRuntime(name: String): NodeRuntime {
        return CodeNodeFactory.createIn2Out1Processor<Any, Any, Any>(
            name = name,
            process = processBlock
        )
    }
}

