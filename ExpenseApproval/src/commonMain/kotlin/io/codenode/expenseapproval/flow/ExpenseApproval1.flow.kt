package io.codenode.expenseapproval.flow

import io.codenode.fbpdsl.dsl.*
import io.codenode.fbpdsl.model.*
import io.codenode.expenseapproval.iptypes.ExpenseAmount
import io.codenode.expenseapproval.iptypes.ApprovalDecision

val expenseApproval1FlowGraph = flowGraph("ExpenseApproval1", version = "1.0.0") {
    val conditionBranch = codeNode("ConditionBranch", nodeType = "TRANSFORMER") {
        position(180.0, 420.0)
        input("amount", ExpenseAmount::class)
        output("approved", ExpenseAmount::class)
        output("escalated", ExpenseAmount::class)
        config("_genericType", "in1out2")
        config("_codeNodeDefinition", "true")
        config("_codeNodeClass", "io.codenode.expenseapproval.nodes.ConditionBranchCodeNode")
    }

    val approve = codeNode("Approve", nodeType = "TRANSFORMER") {
        position(420.0, 300.0)
        input("amount", ExpenseAmount::class)
        output("decision", ApprovalDecision::class)
        config("_genericType", "in1out1")
        config("_codeNodeDefinition", "true")
        config("_codeNodeClass", "io.codenode.expenseapproval.nodes.ApproveCodeNode")
    }

    val escalate = codeNode("Escalate", nodeType = "TRANSFORMER") {
        position(420.0, 540.0)
        input("amount", ExpenseAmount::class)
        output("decision", ApprovalDecision::class)
        config("_genericType", "in1out1")
        config("_codeNodeDefinition", "true")
        config("_codeNodeClass", "io.codenode.expenseapproval.nodes.EscalateCodeNode")
    }

    val mergeDecision = codeNode("MergeDecision", nodeType = "MERGER") {
        description = "Merger node with 2 input(s) and 1 output(s)"
        position(660.0, 420.0)
        input("input1", ApprovalDecision::class)
        input("input2", ApprovalDecision::class)
        output("output1", ApprovalDecision::class)
        config("_genericType", "in2out1")
        config("_codeNodeDefinition", "true")
        config("_codeNodeClass", "io.codenode.expenseapproval.nodes.MergeDecisionCodeNode")
    }

    conditionBranch.output("approved") connect approve.input("amount") withType "ip_expenseamount"
    conditionBranch.output("escalated") connect escalate.input("amount") withType "ip_expenseamount"
    approve.output("decision") connect mergeDecision.input("input1") withType "ip_approvaldecision"
    escalate.output("decision") connect mergeDecision.input("input2") withType "ip_approvaldecision"
}
