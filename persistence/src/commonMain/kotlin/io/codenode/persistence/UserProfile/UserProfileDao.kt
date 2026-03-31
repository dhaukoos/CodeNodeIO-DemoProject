package io.codenode.persistence.userprofile

import androidx.room.Dao
import androidx.room.Query
import io.codenode.persistence.BaseDao
import kotlinx.coroutines.flow.Flow

@Dao
interface UserProfileDao : BaseDao<UserProfileEntity> {
    @Query("SELECT * FROM userprofiles")
    fun getAllAsFlow(): Flow<List<UserProfileEntity>>
}
