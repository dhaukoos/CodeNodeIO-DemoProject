package io.codenode.expenseapproval.nodes

import io.codenode.fbpdsl.model.CodeNodeFactory
import io.codenode.fbpdsl.model.CodeNodeType
import io.codenode.fbpdsl.runtime.*
import kotlin.reflect.KClass

object DecisionSinkCodeNode : CodeNodeDefinition {
    override val name = "DecisionSink"
    override val category = CodeNodeType.SINK
    override val description = "Sink node with 1 input(s) and 0 output(s)"
    override val inputPorts = listOf(PortSpec("input1", Any::class))
    override val outputPorts = emptyList<PortSpec>()

    // TODO: Replace with your processing logic
    private val processBlock: ContinuousSinkBlock<Any> = { input -> /* consume */ }

    override fun createRuntime(name: String): NodeRuntime {
        return CodeNodeFactory.createContinuousSink<Any>(
            name = name,
            consume = processBlock
        )
    }
}

