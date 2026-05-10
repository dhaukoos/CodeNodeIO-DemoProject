package io.codenode.greeter.flow

import io.codenode.fbpdsl.dsl.*
import io.codenode.fbpdsl.model.*

val greeterFlowGraph = flowGraph("Greeter", version = "1.0.0") {
    val greeterSink = codeNode("GreeterSink", nodeType = "SINK") {
        position(685.0, 276.0)
        input("greeting", String::class)
        input("nameLength", Int::class)
        config("_codeNodeClass", "io.codenode.greeter.nodes.GreeterSinkCodeNode")
        config("_codeNodeDefinition", "true")
    }

    val greeterSource = codeNode("GreeterSource", nodeType = "SOURCE") {
        position(151.0, 280.0)
        output("name", String::class)
        config("_codeNodeClass", "io.codenode.greeter.nodes.GreeterSourceCodeNode")
        config("_codeNodeDefinition", "true")
    }

    val greeting2 = codeNode("Greeting2", nodeType = "TRANSFORMER") {
        position(400.0, 278.0)
        input("input", String::class)
        output("output1", String::class)
        output("output2", Int::class)
        config("_genericType", "in1out2")
        config("_codeNodeClass", "io.codenode.greeter.nodes.Greeting2CodeNode")
        config("_codeNodeDefinition", "true")
    }

    greeterSource.output("name") connect greeting2.input("input") withType "ip_string"
    greeting2.output("output1") connect greeterSink.input("greeting") withType "ip_string"
    greeting2.output("output2") connect greeterSink.input("nameLength") withType "ip_int"
}
