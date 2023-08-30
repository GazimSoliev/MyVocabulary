package com.gazim.myvocabluary.app.model

data class WordWithLinks(
    val word: Word,
    val links: List<Link>,
)
