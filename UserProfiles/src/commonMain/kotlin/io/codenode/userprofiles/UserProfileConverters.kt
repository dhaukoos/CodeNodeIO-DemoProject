/*
 * UserProfileConverters - Conversion extensions between IP type and persistence entity
 * License: Apache 2.0
 */

package io.codenode.userprofiles

import io.codenode.iptypes.UserProfile
import io.codenode.persistence.userprofile.UserProfileEntity

/** Convert IP type to persistence entity */
fun UserProfile.toEntity() = UserProfileEntity(id = id, name = name, age = age, isActive = isActive)

/** Convert persistence entity to IP type */
fun UserProfileEntity.toUserProfile() = UserProfile(id = id, name = name, age = age, isActive = isActive)
