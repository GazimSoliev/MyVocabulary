package com.gazim.myvocabluary.data

import com.gazim.myvocabluary.app.model.ILinkID
import com.gazim.myvocabluary.app.model.IWord
import com.gazim.myvocabluary.app.model.IWordID
import com.gazim.myvocabluary.app.model.IWordWithLinks
import kotlinx.datetime.LocalDateTime

interface IDatabaseRepository {
    suspend fun getWords(): List<IWordID>
    suspend fun getLinks(wordId: Int): List<ILinkID>
    suspend fun insertWord(word: IWordID): IWordID
    suspend fun insertWords(words: List<IWord>, createdAt: LocalDateTime): List<IWordWithLinks>
    suspend fun insertLink(link: ILinkID): ILinkID
    suspend fun getWordWithLinks(wordId: Int): IWordWithLinks
    suspend fun insertLinks(links: List<ILinkID>): List<ILinkID>
    suspend fun getRandomWordIds(): List<Int>
    suspend fun getWordById(wordId: Int): IWordID
    suspend fun deleteWordsWithLinks(words: List<IWordID>)
}
