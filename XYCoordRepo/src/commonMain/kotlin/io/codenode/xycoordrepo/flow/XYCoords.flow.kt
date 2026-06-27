package io.codenode.xycoordrepo.flow

import io.codenode.fbpdsl.dsl.*
import io.codenode.fbpdsl.model.*

val xYCoordsFlowGraph = flowGraph("XYCoords", version = "1.0.0") {
    targetPlatform(FlowGraph.TargetPlatform.KMP_ANDROID)
    targetPlatform(FlowGraph.TargetPlatform.KMP_IOS)

    val xYCoordRepository = codeNode("XYCoordRepository", nodeType = "TRANSFORMER") {
        position(445.75, 398.0)
        input("save", Any::class)
        input("update", Any::class)
        input("remove", Any::class)
        output("result", Any::class)
        output("error", Any::class)
        config("_codeNodeClass", "io.codenode.xycoordrepo.nodes.XYCoordRepositoryCodeNode")
        config("_genericType", "in3anyout2")
    }

    val xYCoordsSource = codeNode("XYCoordsSource", nodeType = "SOURCE") {
        position(118.0, 394.25)
        output("save", Any::class)
        output("update", Any::class)
        output("remove", Any::class)
        config("_codeNodeClass", "io.codenode.xycoordrepo.nodes.XYCoordsSourceCodeNode")
        config("_genericType", "in0out3")
    }

    val xYCoordsSink = codeNode("XYCoordsSink", nodeType = "SINK") {
        position(799.5, 398.0)
        input("result", Any::class)
        input("error", Any::class)
        config("_codeNodeClass", "io.codenode.xycoordrepo.nodes.XYCoordsSinkCodeNode")
        config("_genericType", "in2anyout0")
    }

    xYCoordsSource.output("save") connect xYCoordRepository.input("save")
    xYCoordsSource.output("update") connect xYCoordRepository.input("update")
    xYCoordsSource.output("remove") connect xYCoordRepository.input("remove")
    xYCoordRepository.output("result") connect xYCoordsSink.input("result")
    xYCoordRepository.output("error") connect xYCoordsSink.input("error")
}
