package com.gazim.myvocabluary.app.model

import com.gazim.myvocabluary.extensions.now
import kotlinx.datetime.LocalDateTime

data class WordID(
    override val word: String = "",
    override val transcription: String = "",
    override val translation: String = "",
    override val createdAt: LocalDateTime = LocalDateTime.now(),
    override val id: Int = 0,
) : IWordID
