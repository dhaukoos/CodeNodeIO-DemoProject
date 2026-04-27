package io.codenode.mobileapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.codenode.stopwatch.createStopWatchRuntime
import io.codenode.stopwatch.flow.stopWatchFlowGraph
import io.codenode.stopwatch.userInterface.StopWatchScreen
import io.codenode.stopwatch.viewmodel.StopWatchViewModel

@Preview
@Composable
private fun StopWatchPreview() {
    val controller = remember { createStopWatchRuntime(stopWatchFlowGraph) }
    val viewModel = remember { StopWatchViewModel(controller) }
    StopWatchScreen(
        viewModel = viewModel,
        minSize = 200.dp
    )
}
