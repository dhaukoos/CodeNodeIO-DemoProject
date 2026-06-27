package io.codenode.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.room.ConstructedBy
import io.codenode.persistence.xycoord.XYCoordEntity
import io.codenode.persistence.xycoord.XYCoordDao
import io.codenode.persistence.address.AddressEntity
import io.codenode.persistence.address.AddressDao
import io.codenode.persistence.userprofile.UserProfileEntity
import io.codenode.persistence.userprofile.UserProfileDao

@Database(entities = [XYCoordEntity::class, AddressEntity::class, UserProfileEntity::class], version = 11)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun xYCoordDao(): XYCoordDao
    abstract fun addressDao(): AddressDao
    abstract fun userProfileDao(): UserProfileDao
}

expect object AppDatabaseConstructor : RoomDatabaseConstructor<AppDatabase>
