package com.gazim.myvocabluary.data.mapper

import com.gazim.myvocabluary.app.model.Link
import com.gazim.myvocabluary.app.model.Word
import com.gazim.myvocabluary.app.model.WordWithLinks
import com.gazim.myvocabluary.data.room.model.LinkDB
import com.gazim.myvocabluary.data.room.model.WordDB
import com.gazim.myvocabluary.data.room.model.WordWithLinksDB

fun WordDB.toWord() = Word(
    id = id,
    word = word,
    transcription = transcription,
    translation = translation,
    createdAt = createdAt
)

fun LinkDB.toLink() = Link(
    id = id,
    link = link,
    wordId = wordId,
)

fun Word.toWordDB() = WordDB(
    id = id,
    word = word,
    transcription = transcription,
    translation = translation,
    createdAt = createdAt
)

fun Link.toLinkDB() = LinkDB(
    id = id,
    link = link,
    wordId = wordId,
)

fun WordWithLinksDB.toWordWithLinks() = WordWithLinks(
    word = word.toWord(),
    links = links.map(LinkDB::toLink),
)
