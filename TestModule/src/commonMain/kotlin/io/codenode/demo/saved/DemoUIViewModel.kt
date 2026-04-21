package io.codenode.demo.saved

import androidx.lifecycle.ViewModel
import io.codenode.demo.iptypes.CalculationResults
import kotlinx.coroutines.flow.StateFlow

class DemoUIViewModel : ViewModel() {

    val results: StateFlow<CalculationResults?> = DemoUIState.resultsFlow

    fun emit(numA: Double, numB: Double) {
        DemoUIState._numA.value = numA
        DemoUIState._numB.value = numB
//        DemoUIState._results.value = CalculationResults(
//            sum = numA + numB,
//            difference = numA - numB,
//            product = numA * numB,
//            quotient = if (numB != 0.0) numA / numB else Double.NaN
//        )
    }

    fun reset() {
        DemoUIState.reset()
    }
}