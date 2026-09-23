package io.codenode.persistence

import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver

object DatabaseModule {
    private val databaseInstance: AppDatabase by lazy {
        getRoomDatabase(getDatabaseBuilder())
    }

    fun getDatabase(): AppDatabase = databaseInstance
}

fun getRoomDatabase(builder: RoomDatabase.Builder<AppDatabase>): AppDatabase {
    return builder
        .setDriver(BundledSQLiteDriver())
        .fallbackToDestructiveMigration(dropAllTables = true)
        .build()
}

expect fun getDatabaseBuilder(): RoomDatabase.Builder<AppDatabase>
