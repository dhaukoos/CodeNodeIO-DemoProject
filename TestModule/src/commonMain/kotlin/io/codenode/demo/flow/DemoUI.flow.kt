package io.codenode.demo.flow

import io.codenode.fbpdsl.dsl.*
import io.codenode.fbpdsl.model.*

val demoUIFlowGraph = flowGraph("DemoUI", version = "1.0.0") {
    val source = codeNode("DemoUISource", nodeType = "SOURCE") {
        position(100.0, 300.0)
        output("a", Any::class)
        output("b", Any::class)
    }

    val sink = codeNode("DemoUISink", nodeType = "SINK") {
        position(600.0, 300.0)
        input("results", Any::class)
    }
}
