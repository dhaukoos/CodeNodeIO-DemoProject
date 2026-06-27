package io.codenode.xycoordrepo

import io.codenode.persistence.xycoord.XYCoordDao
import io.codenode.persistence.xycoord.XYCoordRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.dsl.module

/**
 * Koin module for XYCoords persistence dependencies.
 * The app layer provides the [XYCoordDao] singleton;
 * this module wires it into a [XYCoordRepository].
 */
val xYCoordsModule = module {
    single { XYCoordRepository(get()) }
}

/**
 * Koin-backed accessor for persistence dependencies.
 * Used by processing logic tick functions that cannot take constructor parameters.
 */
object XYCoordsPersistence : KoinComponent {
    val dao: XYCoordDao by inject()
}
