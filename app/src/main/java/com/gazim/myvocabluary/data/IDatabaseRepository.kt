package com.gazim.myvocabluary.data

import com.gazim.myvocabluary.app.model.LinkID
import com.gazim.myvocabluary.app.model.Word
import com.gazim.myvocabluary.app.model.WordID
import com.gazim.myvocabluary.app.model.WordWithLinks
import kotlinx.datetime.LocalDateTime

interface IDatabaseRepository {
    suspend fun getWords(): List<WordID>
    suspend fun getLinks(wordId: Int): List<LinkID>
    suspend fun insertWord(word: WordID): WordID
    suspend fun insertWords(words: List<Word>, createdAt: LocalDateTime): List<WordWithLinks>
    suspend fun insertLink(link: LinkID): LinkID
    suspend fun getWordWithLinks(wordId: Int): WordWithLinks
    suspend fun insertLinks(links: List<LinkID>): List<LinkID>
    suspend fun getRandomWordIds(): List<Int>
    suspend fun getWordById(wordId: Int): WordID
    suspend fun deleteWordsWithLinks(words: List<WordID>)
}
