package io.codenode.persistence.xycoord

import kotlinx.coroutines.flow.Flow

class XYCoordRepository(private val xYCoordDao: XYCoordDao) {
    suspend fun save(item: XYCoordEntity) = xYCoordDao.insert(item)

    suspend fun update(item: XYCoordEntity) = xYCoordDao.update(item)

    suspend fun remove(item: XYCoordEntity) = xYCoordDao.delete(item)

    fun observeAll(): Flow<List<XYCoordEntity>> = xYCoordDao.getAllAsFlow()
}
