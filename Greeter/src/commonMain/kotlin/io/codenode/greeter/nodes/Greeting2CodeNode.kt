package io.codenode.greeter.nodes

import io.codenode.fbpdsl.model.CodeNodeFactory
import io.codenode.fbpdsl.model.CodeNodeType
import io.codenode.fbpdsl.runtime.*

object Greeting2CodeNode : CodeNodeDefinition {
    override val name = "Greeting2"
    override val category = CodeNodeType.TRANSFORMER
    override val description = "Transforms a name into (greeting, nameLength)"
    override val inputPorts = listOf(PortSpec("input", String::class))
    override val outputPorts = listOf(
        PortSpec("output1", String::class),
        PortSpec("output2", Int::class)
    )

    private val processBlock: In1Out2ProcessBlock<String, String, Int> = { name ->
        val trimmed = name.trim()
        val greeting = if (trimmed.isEmpty()) "Hello, stranger!" else "Hello, $trimmed!"
        ProcessResult2(greeting, trimmed.length)
    }

    override fun createRuntime(name: String): NodeRuntime {
        return CodeNodeFactory.createIn1Out2Processor<String, String, Int>(
            name = name,
            process = processBlock
        )
    }
}
