package com.gazim.myvocabluary.app.model

data class Word(
    override val word: String = "",
    override val transcription: String = "",
    override val translation: String = "",
    override val links: List<String> = emptyList(),
) : IWord
