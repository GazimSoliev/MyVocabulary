package com.gazim.myvocabluary.data.room.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gazim.myvocabluary.extensions.now
import kotlinx.datetime.LocalDateTime

@Entity
data class WordDB(
    val word: String = "",
    val transcription: String = "",
    val translation: String = "",
    val createdAt: LocalDateTime = LocalDateTime.now(),
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
)
