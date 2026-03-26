package io.codenode.launcher

import io.codenode.grapheditor.main

fun main(args: Array<String>) {
    // Delegate to graphEditor main, passing the project directory
    val projectDir = System.getProperty("user.dir")
    val allArgs = args.toMutableList()
    if (!allArgs.contains("--project")) {
        allArgs.add("--project")
        allArgs.add(projectDir)
    }
    main(allArgs.toTypedArray())
}
