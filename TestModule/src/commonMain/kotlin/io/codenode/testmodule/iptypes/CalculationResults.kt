/*
 * CalculationResults - Custom IP Type
 * @IPType
 * @TypeName CalculationResults
 * @TypeId ip_calculationresults
 * @Color rgb(121, 85, 72)
 * License: Apache 2.0
 */

package io.codenode.testmodule.iptypes

data class CalculationResults(
    val sum: Double,
    val difference: Double,
    val product: Double,
    val quotient: Double
)
