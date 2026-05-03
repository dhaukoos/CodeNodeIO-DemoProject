package io.codenode.testmodule.viewmodel

sealed interface DemoUIAction {
    data object Emit: DemoUIAction
}