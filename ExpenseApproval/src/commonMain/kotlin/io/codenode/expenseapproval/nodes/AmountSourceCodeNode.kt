package io.codenode.expenseapproval.nodes

import io.codenode.fbpdsl.model.CodeNodeFactory
import io.codenode.fbpdsl.model.CodeNodeType
import io.codenode.fbpdsl.runtime.*
import kotlin.reflect.KClass

object AmountSourceCodeNode : CodeNodeDefinition {
    override val name = "AmountSource"
    override val category = CodeNodeType.SOURCE
    override val description = "Source node with 0 input(s) and 1 output(s)"
    override val inputPorts = emptyList<PortSpec>()
    override val outputPorts = listOf(PortSpec("output1", Any::class))

    // TODO: Replace with your processing logic
    private val processBlock: ContinuousSourceBlock<Any> = { emit -> emit(Unit) }

    override fun createRuntime(name: String): NodeRuntime {
        return CodeNodeFactory.createContinuousSource<Any>(
            name = name,
            generate = processBlock
        )
    }
}

