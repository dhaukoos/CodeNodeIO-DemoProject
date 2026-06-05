/*
 * ExpenseApproval flowGraph DSL
 *
 * Visual narrative: an Amount enters from the UI (Submit Expense), reaches
 * the ConditionBranch which forks left (auto-approved) when amount <= $100
 * or right (escalated to manager) when amount > $100. The branch produces
 * an ApprovalDecision that flows back to the UI status card.
 *
 * The UI-edge nodes (ExpenseApprovalSource, ExpenseApprovalSink) are
 * produced by the FBP <=> UI Input codegen path; their FQN strings below
 * point to the classes that codegen emits.
 *
 * License: Apache 2.0
 */

package io.codenode.expenseapproval.flow

import io.codenode.fbpdsl.dsl.*
import io.codenode.fbpdsl.model.*
import io.codenode.expenseapproval.iptypes.ApprovalDecision
import io.codenode.expenseapproval.iptypes.ExpenseAmount

val expenseApprovalFlowGraph = flowGraph("ExpenseApproval", version = "1.0.0") {
    targetPlatform(FlowGraph.TargetPlatform.KMP_ANDROID)
    targetPlatform(FlowGraph.TargetPlatform.KMP_IOS)

    val expenseApprovalSource = codeNode("ExpenseApprovalSource", nodeType = "SOURCE") {
        position(120.0, 300.0)
        output("amount", ExpenseAmount::class)
        config("_codeNodeClass", "io.codenode.expenseapproval.nodes.ExpenseApprovalSourceCodeNode")
        config("_codeNodeDefinition", "true")
        config("_genericType", "in0out1")
    }

    val conditionBranch = codeNode("ConditionBranch", nodeType = "TRANSFORMER") {
        position(360.0, 300.0)
        input("amount", ExpenseAmount::class)
        output("approved", ExpenseAmount::class)
        output("escalated", ExpenseAmount::class)
        config("_codeNodeClass", "io.codenode.expenseapproval.nodes.ConditionBranchCodeNode")
        config("_codeNodeDefinition", "true")
        config("_genericType", "in1out2")
    }

    val approveNode = codeNode("Approve", nodeType = "TRANSFORMER") {
        position(600.0, 180.0)
        input("amount", ExpenseAmount::class)
        output("decision", ApprovalDecision::class)
        config("_codeNodeClass", "io.codenode.expenseapproval.nodes.ApproveCodeNode")
        config("_codeNodeDefinition", "true")
        config("_genericType", "in1out1")
    }

    val escalateNode = codeNode("Escalate", nodeType = "TRANSFORMER") {
        position(600.0, 420.0)
        input("amount", ExpenseAmount::class)
        output("decision", ApprovalDecision::class)
        config("_codeNodeClass", "io.codenode.expenseapproval.nodes.EscalateCodeNode")
        config("_codeNodeDefinition", "true")
        config("_genericType", "in1out1")
    }

    val expenseApprovalSink = codeNode("ExpenseApprovalSink", nodeType = "SINK") {
        position(840.0, 300.0)
        input("decision", ApprovalDecision::class)
        config("_codeNodeClass", "io.codenode.expenseapproval.nodes.ExpenseApprovalSinkCodeNode")
        config("_codeNodeDefinition", "true")
        config("_genericType", "in1anyout0")
    }

    expenseApprovalSource.output("amount")    connect conditionBranch.input("amount")  withType "ip_expenseamount"
    conditionBranch.output("approved")        connect approveNode.input("amount")      withType "ip_expenseamount"
    conditionBranch.output("escalated")       connect escalateNode.input("amount")     withType "ip_expenseamount"
    approveNode.output("decision")            connect expenseApprovalSink.input("decision") withType "ip_approvaldecision"
    escalateNode.output("decision")           connect expenseApprovalSink.input("decision") withType "ip_approvaldecision"
}
