/*
 * ImageProcessingUtils.ios.kt
 * EdgeArtFilter iOS stub (feature 130)
 * License: Apache 2.0
 */

package io.codenode.edgeartfilter

import androidx.compose.ui.graphics.ImageBitmap
import io.codenode.edgeartfilter.iptypes.ImageData

/**
 * iOS stub — EdgeArtFilter is Desktop-primary at runtime. iOS is a
 * compile-only target for KMP-compliance (per feedback_kmp_first constraint).
 * Shared across all 3 iOS variants (iosX64, iosArm64, iosSimulatorArm64) via
 * `applyDefaultHierarchyTemplate()` intermediate source-set inheritance.
 * See feature 130 spec for the design decision (AskUserQuestion Option A,
 * 2026-09-23) — real iOS UIImage/Skia bridging is feature 133 candidacy.
 */
actual fun createImageBitmapFromPixels(pixels: IntArray, width: Int, height: Int): ImageBitmap {
    throw UnsupportedOperationException(
        "EdgeArtFilter is Desktop-only at runtime; Android/iOS variants are compile targets for KMP-compliance, not runtime targets."
    )
}

/**
 * iOS stub — matches the "user cancels" branch of the JVM implementation.
 * No file-picker available on non-Desktop targets (feature 133 candidacy).
 */
actual fun pickImageFile(): ImageData? = null
