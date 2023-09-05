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
    @Insert
    suspend fun insertWord(word: WordDB): Long

    @Insert
    suspend fun insertLink(link: LinkDB): Long

    @Insert
    suspend fun insertLinks(links: List<LinkDB>): List<Long>

    @Transaction
    @Query("select * from WordDB where id = :wordId")
    suspend fun getWordsWithLink(wordId: Int): WordWithLinksDB

    @Query("select * from WordDB")
    suspend fun getWords(): List<WordDB>

    @Query("select * from LinkDB where wordId = :wordId")
    suspend fun getLinks(wordId: Int): List<LinkDB>

    @Query("select id from WordDB")
    suspend fun getWordIds(): List<Int>

    @Query("select * from WordDB where id = :id")
    suspend fun getWordById(id: Int): WordDB
}
