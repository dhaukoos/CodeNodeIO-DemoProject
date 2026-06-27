/*
 * GraphNode Template: GroupTest2
 * @GraphNodeTemplate
 * @TemplateName GroupTest2
 * @Description Grouped from 3 nodes
 * @InputPorts 1
 * @OutputPorts 1
 * @ChildNodes 3
 * Created: 2026-06-14T00:57:26.455287
 * License: Apache 2.0
 */

package graphnodes

/*
 * Flow Graph: GroupTest2
 * Version: 1.0.0
 * Description: Grouped from 3 nodes
 * Generated: 2026-06-14T00:57:26.459971
 */

import io.codenode.fbpdsl.dsl.*
import io.codenode.fbpdsl.model.*

val graph = flowGraph("GroupTest2", version = "1.0.0", description = "Grouped from 3 nodes") {
    // Nodes
    val grouptest2 = graphNode("GroupTest2") {
        description = "Grouped from 3 nodes"
        position(300.0, 200.0)

        // Child nodes
        val child_approve = codeNode("Approve") {
            position(290.0, 50.0)
            input("amount", ExpenseAmount::class)
            output("decision", ApprovalDecision::class)
            config("_genericType", "in1out1")
            config("_codeNodeDefinition", "true")
            config("_codeNodeClass", "io.codenode.expenseapproval.nodes.ApproveCodeNode")
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

        val child_escalate_2 = codeNode("Escalate") {
            position(290.0, 290.0)
            input("amount", ExpenseAmount::class)
            output("decision", ApprovalDecision::class)
            config("_genericType", "in1out1")
            config("_codeNodeDefinition", "true")
            config("_codeNodeClass", "io.codenode.expenseapproval.nodes.EscalateCodeNode")
        }

        // Internal connections
        internalConnection(child_conditionbranch_1, "approved", child_approve, "amount") withType "ip_expenseamount"
        internalConnection(child_conditionbranch_1, "escalated", child_escalate_2, "amount") withType "ip_expenseamount"

        // Port mappings
        portMapping("amount_in", "child_conditionbranch_1", "amount")
        portMapping("decision_out", "child_approve", "decision")

        // Exposed input ports
        exposeInput("amount_in", ExpenseAmount::class)

        // Exposed output ports
        exposeOutput("decision_out", ApprovalDecision::class)
    }

}
