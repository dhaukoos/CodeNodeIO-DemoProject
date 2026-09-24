/*
 * ImageProcessingUtils.android.kt
 * EdgeArtFilter Android stub (feature 130)
 * License: Apache 2.0
 */

package io.codenode.edgeartfilter

import androidx.compose.ui.graphics.ImageBitmap
import io.codenode.edgeartfilter.iptypes.ImageData

/**
 * Android stub — EdgeArtFilter is Desktop-primary at runtime. Android is a
 * compile-only target for KMP-compliance (per feedback_kmp_first constraint).
 * See feature 130 spec for the design decision (AskUserQuestion Option A,
 * 2026-09-23) — real Android bitmap conversion is feature 132 candidacy.
 */
actual fun createImageBitmapFromPixels(pixels: IntArray, width: Int, height: Int): ImageBitmap {
    throw UnsupportedOperationException(
        "EdgeArtFilter is Desktop-only at runtime; Android/iOS variants are compile targets for KMP-compliance, not runtime targets."
    )
}

/**
 * Android stub — matches the "user cancels" branch of the JVM implementation.
 * No file-picker available on non-Desktop targets (feature 132 candidacy).
 */
actual fun pickImageFile(): ImageData? = null
