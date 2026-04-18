/*
 * DemoUIPreview - Android Studio preview for DemoUI
 * License: Apache 2.0
 */

package io.codenode.demo

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import io.codenode.demo.userInterface.DemoUI

@Preview
@Composable
private fun DemoUIPreview() {
    MaterialTheme {
        Surface {
            DemoUI()
        }
    }
}
