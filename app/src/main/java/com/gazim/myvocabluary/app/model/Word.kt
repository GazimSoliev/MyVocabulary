package com.gazim.myvocabluary.app.model

data class Word(
    val word: String = "",
    val transcription: String = "",
    val translation: String = "",
    val links: List<String> = emptyList(),
)
