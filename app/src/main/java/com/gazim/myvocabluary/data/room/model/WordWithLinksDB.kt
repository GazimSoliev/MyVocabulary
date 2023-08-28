package com.gazim.myvocabluary.data.room.model

import androidx.room.Embedded
import androidx.room.Relation

data class WordWithLinksDB(
    @Embedded val word: WordDB,
    @Relation(parentColumn = "id", entityColumn = "wordId")
    val links: List<LinkDB>
)