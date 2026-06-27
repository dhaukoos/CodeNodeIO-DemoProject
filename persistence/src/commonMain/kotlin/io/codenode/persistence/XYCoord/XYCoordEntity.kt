package io.codenode.persistence.xycoord

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "xycoords")
data class XYCoordEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val X: Double,
    val Y: Double
)
