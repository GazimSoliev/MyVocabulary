package com.gazim.myvocabluary.data

import com.gazim.myvocabluary.app.model.LinkID
import com.gazim.myvocabluary.app.model.WordID
import com.gazim.myvocabluary.app.model.WordWithLinks

interface IDatabaseRepository {
    suspend fun getWords(): List<WordID>
    suspend fun getLinks(wordId: Int): List<LinkID>
    suspend fun insertWord(word: WordID): WordID
    suspend fun insertLink(link: LinkID): LinkID
    suspend fun getWordWithLinks(wordId: Int): WordWithLinks
    suspend fun insertLinks(links: List<LinkID>): List<LinkID>
    suspend fun getRandomWordIds(): List<Int>
    suspend fun getWordById(wordId: Int): WordID
}
