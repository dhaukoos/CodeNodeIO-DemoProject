package io.codenode.testmodule.nodes

import io.codenode.fbpdsl.model.CodeNodeFactory
import io.codenode.fbpdsl.model.CodeNodeType
import io.codenode.fbpdsl.runtime.*
import io.codenode.testmodule.iptypes.CalculationResults

object Calc2CodeNode : CodeNodeDefinition {
    override val name = "Calc2"
    override val category = CodeNodeType.TRANSFORMER
    override val description = "Transformer node with 2 input(s) and 1 output(s)"
    override val inputPorts = listOf(PortSpec("input1", Double::class), PortSpec("input2", Double::class))
    override val outputPorts = listOf(PortSpec("output1", CalculationResults::class))

    // TODO: Replace with your processing logic
    private val processBlock: In2Out1ProcessBlock<Double, Double, CalculationResults> = { input1, input2 ->
        val sum = input1 + input2
        val difference = input1 - input2
        val product = input1 * input2
        val quotient = input1 / input2
        CalculationResults(sum, difference, product, quotient)
      }

    override fun createRuntime(name: String): NodeRuntime {
        return CodeNodeFactory.createIn2Out1Processor<Double, Double, CalculationResults>(
            name = name,
            process = processBlock
        )
    }
}

