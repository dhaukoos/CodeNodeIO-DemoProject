package io.codenode.expenseapproval.flow

import io.codenode.fbpdsl.dsl.*
import io.codenode.fbpdsl.model.*

val expenseApproval1FlowGraph = flowGraph("ExpenseApproval1", version = "1.0.0") {
    val escalate = codeNode("Escalate", nodeType = "TRANSFORMER") {
        position(420.0, 540.0)
        input("amount", ExpenseAmount::class)
        output("decision", ApprovalDecision::class)
        config("_genericType", "in1out1")
        config("_codeNodeDefinition", "true")
        config("_codeNodeClass", "io.codenode.expenseapproval.nodes.EscalateCodeNode")
    }

    val approve = codeNode("Approve", nodeType = "TRANSFORMER") {
        position(420.0, 300.0)
        input("amount", ExpenseAmount::class)
        output("decision", ApprovalDecision::class)
        config("_genericType", "in1out1")
        config("_codeNodeDefinition", "true")
        config("_codeNodeClass", "io.codenode.expenseapproval.nodes.ApproveCodeNode")
    }

    val conditionBranch = codeNode("ConditionBranch", nodeType = "TRANSFORMER") {
        position(180.0, 420.0)
        input("amount", ExpenseAmount::class)
        output("approved", ExpenseAmount::class)
        output("escalated", ExpenseAmount::class)
        config("_genericType", "in1out2")
        config("_codeNodeDefinition", "true")
        config("_codeNodeClass", "io.codenode.expenseapproval.nodes.ConditionBranchCodeNode")
    }

    conditionBranch.output("approved") connect approve.input("amount")
    conditionBranch.output("escalated") connect escalate.input("amount")
}
