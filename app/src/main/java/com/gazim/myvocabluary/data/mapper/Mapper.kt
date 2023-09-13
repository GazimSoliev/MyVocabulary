package com.gazim.myvocabluary.data.mapper

import com.gazim.myvocabluary.app.model.LinkID
import com.gazim.myvocabluary.app.model.WordID
import com.gazim.myvocabluary.app.model.WordWithLinks
import com.gazim.myvocabluary.data.room.model.LinkDB
import com.gazim.myvocabluary.data.room.model.WordDB
import com.gazim.myvocabluary.data.room.model.WordWithLinksDB

fun WordDB.toWord() = WordID(
    id = id,
    word = word,
    transcription = transcription,
    translation = translation,
    createdAt = createdAt,
)

fun LinkDB.toLink() = LinkID(
    id = id,
    link = link,
    wordId = wordId,
)

fun WordID.toWordDB() = WordDB(
    id = id,
    word = word,
    transcription = transcription,
    translation = translation,
    createdAt = createdAt,
)

fun LinkID.toLinkDB() = LinkDB(
    id = id,
    link = link,
    wordId = wordId,
)

fun WordWithLinksDB.toWordWithLinks() = WordWithLinks(
    word = word.toWord(),
    links = links.map(LinkDB::toLink),
)
