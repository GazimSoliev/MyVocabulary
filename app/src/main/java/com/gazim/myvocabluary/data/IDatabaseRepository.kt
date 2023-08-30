package com.gazim.myvocabluary.data

import com.gazim.myvocabluary.app.model.Link
import com.gazim.myvocabluary.app.model.Word
import com.gazim.myvocabluary.app.model.WordWithLinks

interface IDatabaseRepository {
    suspend fun getWords(): List<Word>
    suspend fun getLinks(wordId: Int): List<Link>
    suspend fun insertWord(word: Word): Word
    suspend fun insertLink(link: Link): Link
    suspend fun getWordWithLinks(wordId: Int): WordWithLinks
    suspend fun insertLinks(links: List<Link>): List<Link>
}