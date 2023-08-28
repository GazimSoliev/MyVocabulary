package com.gazim.myvocabluary.data.room.model

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
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val wordId: Int,
    val link: String,
)
