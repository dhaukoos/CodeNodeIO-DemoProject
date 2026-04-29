/*
 * DemoUIPreview - Android Studio preview for DemoUI
 * License: Apache 2.0
 */

package io.codenode.testmodule

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import io.codenode.testmodule.saved.DemoUIViewModel
import io.codenode.testmodule.userInterface.DemoUI

@Preview
@Composable
private fun DemoUIPreview() {
    val viewModel = remember { DemoUIViewModel() }
    MaterialTheme {
        Surface {
            DemoUI(viewModel = viewModel)
        }
    }
}
