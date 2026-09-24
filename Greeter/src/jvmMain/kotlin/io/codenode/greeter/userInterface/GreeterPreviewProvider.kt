/*
 * GreeterPreviewProvider — Provides Greeter preview composable for the runtime panel
 * License: Apache 2.0
 */

package io.codenode.greeter.userInterface

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import io.codenode.greeter.viewmodel.GreeterViewModel
import io.codenode.previewapi.PreviewComposable
import io.codenode.previewapi.PreviewSlot

/**
 * Provides preview composables that render Greeter components,
 * driven by the RuntimeSession's ViewModel state.
 *
 * Per feature 087 / Design B, the preview lambda is the host-app
 * "ScreenRoot" seam: it collects `viewModel.state` as Compose state and
 * passes `viewModel::onEvent` to the pure two-parameter Greeter Screen.
 */
object GreeterPreviewProvider {

    val preview: PreviewComposable = { viewModel, modifier ->
        val vm = viewModel as GreeterViewModel
        val state by vm.state.collectAsState()
        Greeter(state = state, onEvent = vm::onEvent, modifier = modifier)
    }

    fun install() {
        PreviewSlot.set(preview)
    }
}
