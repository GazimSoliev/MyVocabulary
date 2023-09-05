package com.gazim.myvocabluary.data

import com.gazim.myvocabluary.app.model.Link
import com.gazim.myvocabluary.app.model.Word
import com.gazim.myvocabluary.app.model.WordWithLinks
import com.gazim.myvocabluary.data.mapper.toLink
import com.gazim.myvocabluary.data.mapper.toLinkDB
import com.gazim.myvocabluary.data.mapper.toWord
import com.gazim.myvocabluary.data.mapper.toWordDB
import com.gazim.myvocabluary.data.mapper.toWordWithLinks
import com.gazim.myvocabluary.data.room.dao.VocabularyDAO
import com.gazim.myvocabluary.data.room.model.LinkDB
import com.gazim.myvocabluary.data.room.model.WordDB

class DatabaseRepository(private val vocabularyDAO: VocabularyDAO) : IDatabaseRepository {

    override suspend fun getWords(): List<Word> = vocabularyDAO.getWords().map(WordDB::toWord)

    override suspend fun getLinks(wordId: Int): List<Link> =
        vocabularyDAO.getLinks(wordId).map(LinkDB::toLink)

    override suspend fun insertWord(word: Word) = vocabularyDAO.insertWord(word.toWordDB()).let { word.copy(id = it.toInt()) }

    override suspend fun insertLink(link: Link) =
        vocabularyDAO.insertLink(link.toLinkDB()).let { link.copy(id = it.toInt()) }

    override suspend fun getWordWithLinks(wordId: Int): WordWithLinks =
        vocabularyDAO.getWordsWithLink(wordId).toWordWithLinks()

    override suspend fun insertLinks(links: List<Link>): List<Link> =
        vocabularyDAO.insertLinks(
            links.map(Link::toLinkDB),
        ).zip(links) { i, l -> l.copy(id = i.toInt()) }

    override suspend fun getRandomWordIds(): List<Int> = vocabularyDAO.getWordIds().shuffled()

    override suspend fun getWordById(wordId: Int): Word = vocabularyDAO.getWordById(wordId).toWord()
}
