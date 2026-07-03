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

    // Feature 111 US1 migration. ConditionBranch selectively routes each IP to
    // EITHER input1 OR input2 (exclusive), and MergeDecision forwards whatever
    // it just received to the single output. This is merger semantics — NOT
    // cached-both-inputs aggregation.
    //
    // Prior implementation used `createIn2AnyOut1Processor` + sentinel + a
    // "first non-sentinel wins" lambda, which read BOTH cached inputs on every
    // receive. After path alternation, that pattern re-emitted the cached
    // Path-A value even when Path B was the one that just fired, producing
    // the "alternating-path Sink deadlock" documented in feature 110 T014 and
    // fixed by feature 111.
    //
    // `createIn2MergerOut1Processor` forwards only the just-received value.
    // Default transform is identity — fine here since we forward Any → Any.
    override fun createRuntime(name: String): NodeRuntime {
        return CodeNodeFactory.createIn2MergerOut1Processor<Any, Any, Any>(
            name = name,
        )
    }
}
