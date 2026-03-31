/*
 * AddressConverters - Conversion extensions between IP type and persistence entity
 * License: Apache 2.0
 */

package io.codenode.addresses

import io.codenode.iptypes.Address
import io.codenode.persistence.AddressEntity

/** Convert IP type to persistence entity */
fun Address.toEntity() = AddressEntity(id = id, Street = street, City = city, State = state, Zip = zip)

/** Convert persistence entity to IP type */
fun AddressEntity.toAddress() = Address(id = id, street = Street, city = City, state = State, zip = Zip)
