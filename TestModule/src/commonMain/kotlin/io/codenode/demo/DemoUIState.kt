/*
 * DemoUIState
 * Shared observable state for the DemoUI module.
 * License: Apache 2.0
 */

package io.codenode.demo

import io.codenode.demo.iptypes.CalculationResults
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

object DemoUIState {

    internal val _numA = MutableStateFlow<Double?>(null)
    val numAFlow: StateFlow<Double?> = _numA.asStateFlow()

    internal val _numB = MutableStateFlow<Double?>(null)
    val numBFlow: StateFlow<Double?> = _numB.asStateFlow()

    internal val _results = MutableStateFlow<CalculationResults?>(null)
    val resultsFlow: StateFlow<CalculationResults?> = _results.asStateFlow()

    fun reset() {
        _numA.value = null
        _numB.value = null
        _results.value = null
    }
}
