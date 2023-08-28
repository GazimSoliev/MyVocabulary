package com.gazim.myvocabluary.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.gazim.myvocabluary.data.room.model.LinkDB
import com.gazim.myvocabluary.data.room.model.WordDB
import com.gazim.myvocabluary.data.room.model.WordWithLinksDB

@Dao
interface VocabularyDAO {

    @Transaction
    suspend fun insertWordWithLinks(wordWithLinks: WordWithLinksDB) {
        insertWord(wordWithLinks.word)
        insertLinks(wordWithLinks.links)
    }

    @Insert
    suspend fun insertWord(word: WordDB)

    @Insert
    suspend fun insertLinks(link: List<LinkDB>)

    @Transaction
    @Query("select * from WordDB")
    suspend fun getWords(): List<WordWithLinksDB>
}
