/*
 * Address - Custom IP Type
 * @IPType
 * @TypeName Address
 * @TypeId ip_address
 * @Color rgb(255, 87, 34)
 * License: Apache 2.0
 */

package io.codenode.iptypes

data class Address(
    val id: Long = 0,
    val street: String,
    val city: String,
    val state: String,
    val zip: Int
)
