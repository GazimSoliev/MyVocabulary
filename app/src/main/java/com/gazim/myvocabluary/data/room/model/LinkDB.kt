package com.gazim.myvocabluary.data.room.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = WordDB::class,
            parentColumns = ["id"],
            childColumns = ["wordId"],
        ),
    ],
)
data class LinkDB(
    val link: String = "",
    @ColumnInfo(index = true)
    val wordId: Int = 0,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
)
