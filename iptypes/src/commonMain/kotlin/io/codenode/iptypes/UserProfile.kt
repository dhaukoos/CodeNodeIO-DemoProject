/*
 * UserProfile - Custom IP Type
 * @IPType
 * @TypeName UserProfile
 * @TypeId ip_userprofile
 * @Color rgb(0, 188, 212)
 * License: Apache 2.0
 */

package io.codenode.iptypes

/**
 * IP type representing a user profile flowing through the pipeline.
 * Decoupled from the Room entity to keep pipeline types independent of persistence.
 */
data class UserProfile(
    val id: Long = 0,
    val name: String,
    val age: Int? = null,
    val isActive: Boolean
)
