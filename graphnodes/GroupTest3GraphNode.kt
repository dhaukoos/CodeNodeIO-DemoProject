/*
 * GraphNode Template: GroupTest3
 * @GraphNodeTemplate
 * @TemplateName GroupTest3
 * @Description Grouped from 4 nodes
 * @InputPorts 1
 * @OutputPorts 1
 * @ChildNodes 4
 * Created: 2026-06-14T15:18:25.584749
 * License: Apache 2.0
 */

package graphnodes

/*
 * Flow Graph: GroupTest3
 * Version: 1.0.0
 * Description: Grouped from 4 nodes
 * Generated: 2026-06-14T15:18:25.587892
 */

import io.codenode.fbpdsl.dsl.*
import io.codenode.fbpdsl.model.*

val graph = flowGraph("GroupTest3", version = "1.0.0", description = "Grouped from 4 nodes") {
    // Nodes
    val grouptest3 = graphNode("GroupTest3") {
        description = "Grouped from 4 nodes"
        position(300.0, 200.0)

        // Child nodes
        val child_conditionbranch = codeNode("ConditionBranch") {
            position(50.0, 170.0)
            input("amount", ExpenseAmount::class)
            output("approved", ExpenseAmount::class)
            output("escalated", ExpenseAmount::class)
            config("_genericType", "in1out2")
            config("_codeNodeDefinition", "true")
            config("_codeNodeClass", "io.codenode.expenseapproval.nodes.ConditionBranchCodeNode")
        }

        val child_approve_1 = codeNode("Approve") {
            position(290.0, 50.0)
            input("amount", ExpenseAmount::class)
            output("decision", ApprovalDecision::class)
            config("_genericType", "in1out1")
            config("_codeNodeDefinition", "true")
            config("_codeNodeClass", "io.codenode.expenseapproval.nodes.ApproveCodeNode")
        }

        val child_escalate_2 = codeNode("Escalate") {
            position(290.0, 290.0)
            input("amount", ExpenseAmount::class)
            output("decision", ApprovalDecision::class)
            config("_genericType", "in1out1")
            config("_codeNodeDefinition", "true")
            config("_codeNodeClass", "io.codenode.expenseapproval.nodes.EscalateCodeNode")
        }

        val child_mergedecision_3 = codeNode("MergeDecision", nodeType = "MERGER") {
            position(530.0, 170.0)
            input("input1", ApprovalDecision::class)
            input("input2", ApprovalDecision::class)
            output("output1", ApprovalDecision::class)
            config("_genericType", "in2out1")
        }

        // Internal connections
        internalConnection(child_conditionbranch, "approved", child_approve_1, "amount") withType "ip_expenseamount"
        internalConnection(child_conditionbranch, "escalated", child_escalate_2, "amount") withType "ip_expenseamount"
        internalConnection(child_approve_1, "decision", child_mergedecision_3, "input1") withType "ip_approvaldecision"
        internalConnection(child_escalate_2, "decision", child_mergedecision_3, "input2") withType "ip_approvaldecision"

        // Port mappings
        portMapping("input1", "child_conditionbranch", "amount")
        portMapping("output1", "child_mergedecision_3", "output1")
        portMapping("amount", "child_conditionbranch", "amount")
        portMapping("decision", "child_mergedecision_3", "output1")

        // Exposed input ports
        exposeInput("amount", ExpenseAmount::class)

        // Exposed output ports
        exposeOutput("decision", ApprovalDecision::class)
    }

}
