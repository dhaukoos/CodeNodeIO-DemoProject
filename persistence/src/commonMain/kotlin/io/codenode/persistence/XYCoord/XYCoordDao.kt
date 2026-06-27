package io.codenode.persistence.xycoord

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import io.codenode.persistence.BaseDao

@Dao
interface XYCoordDao : BaseDao<XYCoordEntity> {
    @Query("SELECT * FROM xycoords")
    fun getAllAsFlow(): Flow<List<XYCoordEntity>>
}
