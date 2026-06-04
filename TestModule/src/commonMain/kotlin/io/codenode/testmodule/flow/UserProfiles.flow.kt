package io.codenode.testmodule.flow

import io.codenode.fbpdsl.dsl.*
import io.codenode.fbpdsl.model.*
import io.codenode.iptypes.UserProfile

val userProfilesFlowGraph = flowGraph("UserProfiles", version = "1.0.0") {
    targetPlatform(FlowGraph.TargetPlatform.KMP_ANDROID)
    targetPlatform(FlowGraph.TargetPlatform.KMP_IOS)

    val userProfileRepository = codeNode("UserProfileRepository", nodeType = "TRANSFORMER") {
        position(420.0, 360.0)
        input("save", UserProfile::class)
        input("update", UserProfile::class)
        input("remove", UserProfile::class)
        output("result", String::class)
        output("error", String::class)
        config("_codeNodeClass", "io.codenode.userprofiles.nodes.UserProfileRepositoryCodeNode")
        config("_codeNodeDefinition", "true")
    }

    val userProfilesDisplay = codeNode("UserProfilesDisplay", nodeType = "SINK") {
        position(660.0, 360.0)
        input("result", String::class)
        input("error", String::class)
        config("_codeNodeClass", "io.codenode.userprofiles.nodes.UserProfilesDisplayCodeNode")
        config("_codeNodeDefinition", "true")
    }

    val userProfileCUD = codeNode("UserProfileCUD", nodeType = "SOURCE") {
        position(180.0, 360.0)
        output("save", UserProfile::class)
        output("update", UserProfile::class)
        output("remove", UserProfile::class)
        config("_codeNodeClass", "io.codenode.userprofiles.nodes.UserProfileCUDCodeNode")
        config("_codeNodeDefinition", "true")
    }

    userProfileCUD.output("save") connect userProfileRepository.input("save") withType "ip_userprofile"
    userProfileCUD.output("update") connect userProfileRepository.input("update") withType "ip_userprofile"
    userProfileCUD.output("remove") connect userProfileRepository.input("remove") withType "ip_userprofile"
    userProfileRepository.output("result") connect userProfilesDisplay.input("result") withType "ip_string"
    userProfileRepository.output("error") connect userProfilesDisplay.input("error") withType "ip_string"
}
