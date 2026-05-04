/*
 * DemoUIPreview - Android Studio preview for DemoUI
 * License: Apache 2.0
 */

package io.codenode.testmodule

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import io.codenode.testmodule.userInterface.DemoUI
import io.codenode.testmodule.viewmodel.DemoUIState

/**
 * Android Studio @Preview for the DemoUI Screen. Per feature 087 / Design B,
 * the Screen is a pure two-parameter Composable, so the preview supplies a
 * static [DemoUIState] snapshot and a no-op event handler — no ViewModel or
 * runtime controller needed.
 */
@Preview
@Composable
private fun DemoUIPreview() {
    MaterialTheme {
        Surface {
            DemoUI(state = DemoUIState(), onEvent = {})
        }
    }
}
