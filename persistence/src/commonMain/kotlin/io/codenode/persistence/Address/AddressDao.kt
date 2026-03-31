package io.codenode.persistence.address

import androidx.room.Dao
import androidx.room.Query
import io.codenode.persistence.BaseDao
import kotlinx.coroutines.flow.Flow

@Dao
interface AddressDao : BaseDao<AddressEntity> {
    @Query("SELECT * FROM addresss")
    fun getAllAsFlow(): Flow<List<AddressEntity>>
}
