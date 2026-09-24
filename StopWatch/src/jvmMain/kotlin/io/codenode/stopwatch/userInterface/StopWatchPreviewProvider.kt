/*
 * StopWatchPreviewProvider - Provides StopWatch preview composable for the runtime panel
 * License: Apache 2.0
 */

package io.codenode.stopwatch.userInterface

import androidx.compose.ui.unit.dp
import io.codenode.stopwatch.viewmodel.StopWatchViewModel
import io.codenode.previewapi.PreviewComposable
import io.codenode.previewapi.PreviewSlot

/**
 * Provides the StopWatchScreen preview composable, driven by the
 * RuntimeSession's ViewModel state.
 *
 * Migration note (feature 130.1): the pre-feature-110 shape registered TWO
 * previews ("StopWatch" pure-component + "StopWatchScreen"). Feature 110's
 * PreviewSlot is single-slot per .flow.kt file, and StopWatch has one
 * (StopWatch.flow.kt). This keeps the StopWatchScreen preview (feature 087
 * Design B canvas-canonical) and drops the standalone pure-component preview.
 */
object StopWatchPreviewProvider {

    val preview: PreviewComposable = { viewModel, modifier ->
        val vm = viewModel as StopWatchViewModel
        StopWatchScreen(
            viewModel = vm,
            modifier = modifier,
            minSize = 200.dp
        )
    }

    fun install() {
        PreviewSlot.set(preview)
    }
}
