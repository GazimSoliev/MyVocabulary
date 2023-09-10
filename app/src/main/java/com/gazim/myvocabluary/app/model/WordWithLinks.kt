package com.gazim.myvocabluary.app.model

data class WordWithLinks(
    val word: WordID = WordID(),
    val links: List<LinkID> = emptyList(),
)
