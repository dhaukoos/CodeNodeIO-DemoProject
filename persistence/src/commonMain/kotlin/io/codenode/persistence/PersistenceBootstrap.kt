/*
 * PersistenceBootstrap - Registers DAOs in Koin for runtime pipeline execution
 * License: Apache 2.0
 */

package io.codenode.persistence

import org.koin.core.context.GlobalContext
import org.koin.dsl.module

/**
 * Bootstrap object that registers all DAOs in Koin with proper type information.
 * Discovered and invoked by the graphEditor at startup via reflection.
 */
object PersistenceBootstrap {

    /**
     * Registers all DAOs from the AppDatabase as Koin singletons.
     * Must be called after Koin is initialized (startKoin).
     */
    fun registerDaos() {
        try {
            val koin = GlobalContext.get()
            val db = DatabaseModule.getDatabase()
            koin.loadModules(listOf(module {
                single<UserProfileDao> { db.userProfileDao() }
                single<GeoLocationDao> { db.geoLocationDao() }
                single<AddressDao> { db.addressDao() }
            }))
        } catch (e: Exception) {
            println("Warning: PersistenceBootstrap.registerDaos() failed: ${e.message}")
        }
    }
}
