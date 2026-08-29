/*
 * AddressesPreviewProvider - Provides Addresses preview composables for the runtime panel
 * License: Apache 2.0
 */

package io.codenode.addresses.userInterface

import io.codenode.addresses.viewmodel.AddressesViewModel
import io.codenode.addresses.userInterface.Addresses
import io.codenode.previewapi.PreviewComposable
import io.codenode.previewapi.PreviewSlot

/**
 * Provides preview composables that render Addresses components,
 * driven by the RuntimeSession's ViewModel state.
 */
object AddressesPreviewProvider {

    val preview: PreviewComposable = { viewModel, modifier ->
        val vm = viewModel as AddressesViewModel
        Addresses(viewModel = vm, modifier = modifier)
    }

    fun install() {
        PreviewSlot.set(preview)
    }
}
