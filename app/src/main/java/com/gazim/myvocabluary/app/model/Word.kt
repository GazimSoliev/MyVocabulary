package com.gazim.myvocabluary.app.model

import com.gazim.myvocabluary.extensions.now
import kotlinx.datetime.LocalDateTime

data class Word(
    val word: String = "",
    val transcription: String = "",
    val translation: String = "",
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val id: Int = 0,
)
