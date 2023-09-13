package com.gazim.myvocabluary.app.model

fun IWordID.toChosenWordID(): IChosenWord = ChosenWordID(
    word = word,
    transcription = transcription,
    translation = translation,
    createdAt = createdAt,
    id = id,
)

fun IChosenWord.toWordID(): IWordID = WordID(
    word = word,
    transcription = transcription,
    translation = translation,
    createdAt = createdAt,
    id = id,
)