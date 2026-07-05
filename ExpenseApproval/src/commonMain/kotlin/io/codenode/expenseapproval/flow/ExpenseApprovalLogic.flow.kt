package io.codenode.expenseapproval.flow

import io.codenode.fbpdsl.dsl.*
import io.codenode.fbpdsl.model.*
import io.codenode.expenseapproval.iptypes.ApprovalDecision
import io.codenode.expenseapproval.iptypes.ExpenseAmount

val expenseApprovalLogicFlowGraph = flowGraph("ExpenseApprovalLogic", version = "1.0.0") {
    val expenseApprovalLogicSource = codeNode("ExpenseApprovalLogicSource", nodeType = "SOURCE") {
        position(100.0, 300.0)
        output("in_conditionbranch_amount", ExpenseAmount::class)
        config("_codeNodeClass", "io.codenode.expenseapproval.nodes.ExpenseApprovalLogicSourceCodeNode")
        config("_codeNodeDefinition", "true")
        config("_genericType", "in0out1")
    }

    val expenseApprovalLogicSink = codeNode("ExpenseApprovalLogicSink", nodeType = "SINK") {
        position(900.0, 300.0)
        input("out_mergedecision_output1", ApprovalDecision::class)
        config("_codeNodeClass", "io.codenode.expenseapproval.nodes.ExpenseApprovalLogicSinkCodeNode")
        config("_codeNodeDefinition", "true")
        config("_genericType", "in1anyout0")
    }

    val expenseApprovalLogic = useGraphNode("EALogic") {
        position(420.0, 300.0)
    }

    expenseApprovalLogicSource.output("in_conditionbranch_amount") connect expenseApprovalLogic.input("in_conditionbranch_amount") withType "ip_expenseamount"
    expenseApprovalLogic.output("out_mergedecision_output1") connect expenseApprovalLogicSink.input("out_mergedecision_output1") withType "ip_approvaldecision"
}
