package com.gazim.myvocabluary.data.room.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WordDB(
    val word: String = "",
    val transcription: String = "",
    val translation: String = "",
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
)
