package io.codenode.testmodule.flow

import io.codenode.fbpdsl.dsl.*
import io.codenode.fbpdsl.model.*
import io.codenode.testmodule.iptypes.CalculationResults

val demoUIFlowGraph = flowGraph("DemoUI", version = "1.0.0") {
    val demoUISink = codeNode("DemoUISink", nodeType = "SINK") {
        position(685.75, 276.0)
        input("results", CalculationResults::class)
        config("_codeNodeClass", "io.codenode.testmodule.nodes.DemoUISinkCodeNode")

        config("_codeNodeDefinition", "true")
    }

    val demoUISource = codeNode("DemoUISource", nodeType = "SOURCE") {
        position(151.7919921875, 279.888671875)
        output("a", Double::class)
        output("b", Double::class)
        output("c", Double::class)
        config("_codeNodeClass", "io.codenode.testmodule.nodes.DemoUISourceCodeNode")
        config("_codeNodeDefinition", "true")
    }

    val calc2 = codeNode("Calc2", nodeType = "TRANSFORMER") {
        position(400.25, 278.25)
        input("input1", Double::class)
        input("input2", Double::class)
        output("output1", CalculationResults::class)
        config("_genericType", "in2out1")
        config("_codeNodeClass", "io.codenode.testmodule.nodes.Calc2CodeNode")
        config("_codeNodeDefinition", "true")
    }

    calc2.output("output1") connect demoUISink.input("results") withType "ip_calculationresults"
    demoUISource.output("a") connect calc2.input("input1") withType "ip_double"
    demoUISource.output("b") connect calc2.input("input2") withType "ip_double"
}
