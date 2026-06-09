package io.codenode.expenseapproval.flow

import io.codenode.fbpdsl.dsl.*
import io.codenode.fbpdsl.model.*
import io.codenode.expenseapproval.iptypes.ApprovalDecision
import io.codenode.expenseapproval.iptypes.ExpenseAmount
import io.codenode.expenseapproval.viewmodel.Approver
import io.codenode.expenseapproval.viewmodel.ExpenseApprovalStatus

val eALogicFlowGraph = flowGraph("EALogic", version = "1.0.0") {
    val eALogicSource = codeNode("EALogicSource", nodeType = "SOURCE") {
        position(100.0, 300.0)
        output("in_conditionbranch_amount", Any::class)
        config("_codeNodeClass", "io.codenode.expenseapproval.nodes.EALogicSourceCodeNode")
        config("_codeNodeDefinition", "true")
        config("_genericType", "in0out1")
    }

    val eALogic = graphNode("EALogic") {
        description = "Grouped from 4 nodes"
        position(420.0, 420.0)
        val child_approve = codeNode("Approve", nodeType = "TRANSFORMER") {
            position(290.0, 50.0)
            input("amount", ExpenseAmount::class)
            output("decision", ApprovalDecision::class)
            config("_genericType", "in1out1")
            config("_codeNodeDefinition", "true")
            config("_codeNodeClass", "io.codenode.expenseapproval.nodes.ApproveCodeNode")
        }

        val child_escalate_1 = codeNode("Escalate", nodeType = "TRANSFORMER") {
            position(290.0, 290.0)
            input("amount", ExpenseAmount::class)
            output("decision", ApprovalDecision::class)
            config("_genericType", "in1out1")
            config("_codeNodeDefinition", "true")
            config("_codeNodeClass", "io.codenode.expenseapproval.nodes.EscalateCodeNode")
        }

        val child_mergeDecision_2 = codeNode("MergeDecision", nodeType = "MERGER") {
            position(530.0, 170.0)
            input("input1", ApprovalDecision::class)
            input("input2", ApprovalDecision::class)
            output("output1", ApprovalDecision::class)
            config("_genericType", "in2out1")
            config("_codeNodeDefinition", "true")
            config("_codeNodeClass", "io.codenode.expenseapproval.nodes.MergeDecisionCodeNode")
        }

        val child_conditionBranch_3 = codeNode("ConditionBranch", nodeType = "TRANSFORMER") {
            position(50.0, 170.0)
            input("amount", ExpenseAmount::class)
            output("approved", ExpenseAmount::class)
            output("escalated", ExpenseAmount::class)
            config("_genericType", "in1out2")
            config("_codeNodeDefinition", "true")
            config("_codeNodeClass", "io.codenode.expenseapproval.nodes.ConditionBranchCodeNode")
        }

        child_conditionBranch_3.output("approved") connect child_approve.input("amount") withType "ip_expenseamount"
        child_conditionBranch_3.output("escalated") connect child_escalate_1.input("amount") withType "ip_expenseamount"
        child_approve.output("decision") connect child_mergeDecision_2.input("input1") withType "ip_approvaldecision"
        child_escalate_1.output("decision") connect child_mergeDecision_2.input("input2") withType "ip_approvaldecision"

        portMapping("out_mergedecision_output1", "child_mergeDecision_2", "output1")
        portMapping("in_conditionbranch_amount", "child_conditionBranch_3", "amount")

        exposeInput("in_conditionbranch_amount", Any::class)
        exposeOutput("out_mergedecision_output1", Any::class)
    }

    val eALogicSink = codeNode("EALogicSink", nodeType = "SINK") {
        position(900.0, 300.0)
        input("out_mergedecision_output1", Any::class)
        config("_codeNodeClass", "io.codenode.expenseapproval.nodes.EALogicSinkCodeNode")
        config("_codeNodeDefinition", "true")
        config("_genericType", "in1anyout0")
    }

    eALogicSource.output("in_conditionbranch_amount") connect eALogic.input("in_conditionbranch_amount")
    eALogic.output("out_mergedecision_output1") connect eALogicSink.input("out_mergedecision_output1")
}
