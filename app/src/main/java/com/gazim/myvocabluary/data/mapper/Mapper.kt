package com.gazim.myvocabluary.data.mapper

import com.gazim.myvocabluary.app.model.ILinkID
import com.gazim.myvocabluary.app.model.IWordID
import com.gazim.myvocabluary.app.model.IWordWithLinks
import com.gazim.myvocabluary.app.model.LinkID
import com.gazim.myvocabluary.app.model.WordID
import com.gazim.myvocabluary.app.model.WordWithLinks
import com.gazim.myvocabluary.data.room.model.LinkDB
import com.gazim.myvocabluary.data.room.model.WordDB
import com.gazim.myvocabluary.data.room.model.WordWithLinksDB

fun WordDB.toWord(): IWordID = WordID(
    id = id,
    word = word,
    transcription = transcription,
    translation = translation,
    createdAt = createdAt,
)

fun LinkDB.toLink(): ILinkID = LinkID(
    id = id,
    link = link,
    wordId = wordId,
)

fun IWordID.toWordDB() = WordDB(
    id = id,
    word = word,
    transcription = transcription,
    translation = translation,
    createdAt = createdAt,
)

fun ILinkID.toLinkDB() = LinkDB(
    id = id,
    link = link,
    wordId = wordId,
)

fun WordWithLinksDB.toWordWithLinks(): IWordWithLinks = WordWithLinks(
    word = word.toWord(),
    links = links.map(LinkDB::toLink),
)
