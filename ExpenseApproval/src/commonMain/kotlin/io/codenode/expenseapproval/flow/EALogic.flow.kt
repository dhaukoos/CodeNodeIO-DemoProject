package io.codenode.expenseapproval.flow

import io.codenode.fbpdsl.dsl.*
import io.codenode.fbpdsl.model.*
import io.codenode.expenseapproval.iptypes.ApprovalDecision
import io.codenode.expenseapproval.iptypes.ExpenseAmount

val eALogicFlowGraph = flowGraph("EALogic", version = "1.0.0") {
    val eALogicSource = codeNode("EALogicSource", nodeType = "SOURCE") {
        position(100.0, 300.0)
        output("in_conditionbranch_amount", ExpenseAmount::class)
        config("_codeNodeClass", "io.codenode.expenseapproval.nodes.EALogicSourceCodeNode")
        config("_codeNodeDefinition", "true")
        config("_genericType", "in0out1")
    }

    val eALogicSink = codeNode("EALogicSink", nodeType = "SINK") {
        position(900.0, 300.0)
        input("out_mergedecision_output1", ApprovalDecision::class)
        config("_codeNodeClass", "io.codenode.expenseapproval.nodes.EALogicSinkCodeNode")
        config("_codeNodeDefinition", "true")
        config("_genericType", "in1anyout0")
    }

    val eALogic = useGraphNode("EALogic") {
        position(420.0, 300.0)
    }

    eALogicSource.output("in_conditionbranch_amount") connect eALogic.input("in_conditionbranch_amount") withType "ip_expenseamount"
    eALogic.output("out_mergedecision_output1") connect eALogicSink.input("out_mergedecision_output1") withType "ip_approvaldecision"
}
