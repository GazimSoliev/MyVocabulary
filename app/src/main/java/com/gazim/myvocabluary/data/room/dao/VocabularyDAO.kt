package com.gazim.myvocabluary.data.room.dao

import androidx.room.Dao
import androidx.room.Delete
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

    suspend fun insertWordDB(word: WordDB) = word.copy(id = insertWord(word).toInt())

    @Insert
    suspend fun insertWords(words: List<WordDB>): List<Long>

    suspend fun insertWordDBs(words: List<WordDB>): List<WordDB> =
        insertWords(words).zip(words) { i, w -> w.copy(id = i.toInt()) }

    @Insert
    suspend fun insertLink(link: LinkDB): Long

    suspend fun insertLinkDB(link: LinkDB): LinkDB =
        link.copy(id = insertLink(link).toInt())

    @Insert
    suspend fun insertLinks(links: List<LinkDB>): List<Long>

    suspend fun insertLinkDBs(links: List<LinkDB>): List<LinkDB> =
        insertLinks(links).zip(links) { i, l -> l.copy(id = i.toInt()) }

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

    @Delete
    suspend fun deleteWords(words: List<WordDB>)

    @Delete
    suspend fun deleteLinks(links: List<LinkDB>)
}
