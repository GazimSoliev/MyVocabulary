package com.gazim.myvocabluary.app.model

import kotlinx.datetime.LocalDateTime

interface IWordID {
    val word: String
    val transcription: String
    val translation: String
    val createdAt: LocalDateTime
    val id: Int
}

interface IChosenWord : IWordID

interface ILinkID {
    val link: String
    val wordId: Int
    val id: Int
}

interface IWordWithLinks {
    val word: IWordID
    val links: List<ILinkID>
}

interface IWord {
    val word: String
    val transcription: String
    val translation: String
    val links: List<String>
}
