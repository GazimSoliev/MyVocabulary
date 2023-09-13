package com.gazim.myvocabluary.data

import com.gazim.myvocabluary.app.model.LinkID
import com.gazim.myvocabluary.app.model.Word
import com.gazim.myvocabluary.app.model.WordID
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

    override suspend fun getWords(): List<WordID> =
        vocabularyDAO.getWords().map(WordDB::toWord)

    override suspend fun getLinks(wordId: Int): List<LinkID> =
        vocabularyDAO.getLinks(wordId).map(LinkDB::toLink)

    override suspend fun insertWord(word: WordID) =
        vocabularyDAO.insertWordDB(word.toWordDB()).toWord()

    override suspend fun insertWords(
        words: List<Word>,
        createdAt: LocalDateTime
    ): List<WordWithLinks> {
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

    override suspend fun insertLink(link: LinkID) =
        vocabularyDAO.insertLinkDB(link.toLinkDB()).toLink()

    override suspend fun getWordWithLinks(wordId: Int): WordWithLinks =
        vocabularyDAO.getWordsWithLink(wordId).toWordWithLinks()

    override suspend fun insertLinks(links: List<LinkID>): List<LinkID> =
        vocabularyDAO.insertLinkDBs(links.map(LinkID::toLinkDB)).map(LinkDB::toLink)

    override suspend fun getRandomWordIds(): List<Int> =
        vocabularyDAO.getWordIds().shuffled()

    override suspend fun getWordById(wordId: Int): WordID =
        vocabularyDAO.getWordById(wordId).toWord()

    override suspend fun deleteWordsWithLinks(words: List<WordID>) {
        vocabularyDAO.deleteLinks(words.flatMap { vocabularyDAO.getLinks(wordId = it.id) })
        vocabularyDAO.deleteWords(words.map(WordID::toWordDB))
    }
}
