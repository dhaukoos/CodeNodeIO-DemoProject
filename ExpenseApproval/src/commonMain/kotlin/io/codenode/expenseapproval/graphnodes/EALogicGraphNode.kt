/*
 * GraphNode Template: EALogic
 * @GraphNodeTemplate
 * @TemplateName EALogic
 * @Description Grouped from 4 nodes
 * @InputPorts 1
 * @OutputPorts 1
 * @ChildNodes 4
 * Created: 2026-06-08T19:41:01.192271
 * License: Apache 2.0
 */

/*
 * Flow Graph: EALogic
 * Version: 1.0.0
 * Description: Grouped from 4 nodes
 * Generated: 2026-06-08T19:41:01.196984
 */

import io.codenode.fbpdsl.dsl.*
import io.codenode.fbpdsl.model.*

val graph = flowGraph("EALogic", version = "1.0.0", description = "Grouped from 4 nodes") {
    // Nodes
    val ealogic = graphNode("EALogic") {
        description = "Grouped from 4 nodes"
        position(420.0, 420.0)

        // Child nodes
        val child_escalate = codeNode("Escalate") {
            position(290.0, 290.0)
            input("amount", ExpenseAmount::class)
            output("decision", ApprovalDecision::class)
            config("_genericType", "in1out1")
            config("_codeNodeDefinition", "true")
            config("_codeNodeClass", "io.codenode.expenseapproval.nodes.EscalateCodeNode")
        }

        val child_conditionbranch_1 = codeNode("ConditionBranch") {
            position(50.0, 170.0)
            input("amount", ExpenseAmount::class)
            output("approved", ExpenseAmount::class)
            output("escalated", ExpenseAmount::class)
            config("_genericType", "in1out2")
            config("_codeNodeDefinition", "true")
            config("_codeNodeClass", "io.codenode.expenseapproval.nodes.ConditionBranchCodeNode")
        }

        val child_mergedecision_2 = codeNode("MergeDecision", nodeType = "MERGER") {
            position(530.0, 170.0)
            input("input1", ApprovalDecision::class)
            input("input2", ApprovalDecision::class)
            output("output1", ApprovalDecision::class)
            config("_genericType", "in2out1")
            config("_codeNodeDefinition", "true")
            config("_codeNodeClass", "io.codenode.expenseapproval.nodes.MergeDecisionCodeNode")
        }

        val child_approve_3 = codeNode("Approve") {
            position(290.0, 50.0)
            input("amount", ExpenseAmount::class)
            output("decision", ApprovalDecision::class)
            config("_genericType", "in1out1")
            config("_codeNodeDefinition", "true")
            config("_codeNodeClass", "io.codenode.expenseapproval.nodes.ApproveCodeNode")
        }

        // Internal connections
        internalConnection(child_conditionbranch_1, "approved", child_approve_3, "amount") withType "ip_expenseamount"
        internalConnection(child_conditionbranch_1, "escalated", child_escalate, "amount") withType "ip_expenseamount"
        internalConnection(child_approve_3, "decision", child_mergedecision_2, "input1") withType "ip_approvaldecision"
        internalConnection(child_escalate, "decision", child_mergedecision_2, "input2") withType "ip_approvaldecision"

        // Port mappings
        portMapping("in_conditionbranch_amount", "child_conditionbranch_1", "amount")
        portMapping("out_mergedecision_output1", "child_mergedecision_2", "output1")

        // Exposed input ports
        exposeInput("in_conditionbranch_amount", Any::class, downstream = "node_conditionbranch:conditionbranch_amount")

        // Exposed output ports
        exposeOutput("out_mergedecision_output1", Any::class, upstream = "node_mergedecision:mergedecision_output1")
    }

}
