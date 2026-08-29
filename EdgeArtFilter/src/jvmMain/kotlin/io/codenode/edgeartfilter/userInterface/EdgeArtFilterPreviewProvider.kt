/*
 * EdgeArtFilterPreviewProvider - Provides EdgeArtFilter preview composable for the runtime panel
 * License: Apache 2.0
 */

package io.codenode.edgeartfilter.userInterface

import io.codenode.edgeartfilter.viewmodel.EdgeArtFilterViewModel
import io.codenode.edgeartfilter.userInterface.EdgeArtFilter
import io.codenode.previewapi.PreviewComposable
import io.codenode.previewapi.PreviewSlot

/**
 * Provides preview composables that render EdgeArtFilter components,
 * driven by the RuntimeSession's ViewModel state.
 */
object EdgeArtFilterPreviewProvider {

    val preview: PreviewComposable = { viewModel, modifier ->
        val vm = viewModel as EdgeArtFilterViewModel
        EdgeArtFilter(viewModel = vm, modifier = modifier)
    }

    fun install() {
        PreviewSlot.set(preview)
    }
}
