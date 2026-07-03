package io.codenode.expenseapproval.flow

import io.codenode.fbpdsl.dsl.*
import io.codenode.fbpdsl.model.*
import io.codenode.expenseapproval.iptypes.ExpenseAmount

// Feature 111 Path E validation — same-module distinct-circuit for SC-004
// session-swap testing. Bare Source-only graph; no useGraphNode, so
// GraphEditorApp.kt's moduleName resolution falls back from
// rootGraphNode?.name to flowGraph.name = "EALogicVariant". No matching
// EALogicVariantViewModel class exists, so RuntimeSession's viewModel
// defaults to Any() — sufficient for observing session-create /
// dispose / cancel-complete diagnostic output.
val eALogicVariantFlowGraph = flowGraph("EALogicVariant", version = "1.0.0") {
    codeNode("EALogicSource", nodeType = "SOURCE") {
        position(100.0, 300.0)
        output("in_conditionbranch_amount", ExpenseAmount::class)
        config("_codeNodeClass", "io.codenode.expenseapproval.nodes.EALogicSourceCodeNode")
        config("_codeNodeDefinition", "true")
        config("_genericType", "in0out1")
    }
}
