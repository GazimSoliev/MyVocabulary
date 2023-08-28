package com.gazim.myvocabluary.data.room.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WordDB(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val word: String,
    val transcription: String,
    val translation: String,
)
