package com.gazim.myvocabluary.app.model

data class VocabularyWord(
    val id: Int = 0,
    val word: String,
    val transcription: String,
    val translation: String,
    val linksOfPronunciation: List<String>,
)
