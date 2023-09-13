package com.gazim.myvocabluary.data

import com.gazim.myvocabluary.app.model.ILinkID
import com.gazim.myvocabluary.app.model.IWord
import com.gazim.myvocabluary.app.model.IWordID
import com.gazim.myvocabluary.app.model.IWordWithLinks
import com.gazim.myvocabluary.app.model.WordWithLinks
import com.gazim.myvocabluary.data.mapper.toLink
import com.gazim.myvocabluary.data.mapper.toLinkDB
import com.gazim.myvocabluary.data.mapper.toWord
import com.gazim.myvocabluary.data.mapper.toWordDB
import com.gazim.myvocabluary.data.mapper.toWordWithLinks
import com.gazim.myvocabluary.data.room.dao.VocabularyDAO
import com.gazim.myvocabluary.data.room.model.LinkDB
import com.gazim.myvocabluary.data.room.model.WordDB
import kotlinx.datetime.LocalDateTime

class DatabaseRepository(private val vocabularyDAO: VocabularyDAO) : IDatabaseRepository {

    override suspend fun getWords(): List<IWordID> =
        vocabularyDAO.getWords().map(WordDB::toWord)

    override suspend fun getLinks(wordId: Int): List<ILinkID> =
        vocabularyDAO.getLinks(wordId).map(LinkDB::toLink)

    override suspend fun insertWord(word: IWordID): IWordID =
        vocabularyDAO.insertWordDB(word.toWordDB()).toWord()

    override suspend fun insertWords(
        words: List<IWord>,
        createdAt: LocalDateTime,
    ): List<IWordWithLinks> {
        val wordDBs = vocabularyDAO.insertWordDBs(
            words.map {
                WordDB(
                    word = it.word,
                    transcription = it.transcription,
                    translation = it.translation,
                    createdAt = createdAt,
                    id = 0,
                )
            },
        )
        val linkDBsOfWords = wordDBs.zip(words) { wDB, w ->
            vocabularyDAO.insertLinkDBs(
                w.links.map {
                    LinkDB(
                        link = it,
                        wordId = wDB.id,
                        id = 0,
                    )
                },
            )
        }
        return wordDBs.zip(linkDBsOfWords) { w, ls ->
            WordWithLinks(
                word = w.toWord(),
                links = ls.map(LinkDB::toLink),
            )
        }
    }

    override suspend fun insertLink(link: ILinkID): ILinkID =
        vocabularyDAO.insertLinkDB(link.toLinkDB()).toLink()

    override suspend fun getWordWithLinks(wordId: Int): IWordWithLinks =
        vocabularyDAO.getWordsWithLink(wordId).toWordWithLinks()

    override suspend fun insertLinks(links: List<ILinkID>): List<ILinkID> =
        vocabularyDAO.insertLinkDBs(links.map(ILinkID::toLinkDB)).map(LinkDB::toLink)

    override suspend fun getRandomWordIds(): List<Int> =
        vocabularyDAO.getWordIds().shuffled()

    override suspend fun getWordById(wordId: Int): IWordID =
        vocabularyDAO.getWordById(wordId).toWord()

    override suspend fun deleteWordsWithLinks(words: List<IWordID>) {
        vocabularyDAO.deleteLinks(words.flatMap { vocabularyDAO.getLinks(wordId = it.id) })
        vocabularyDAO.deleteWords(words.map(IWordID::toWordDB))
    }
}
