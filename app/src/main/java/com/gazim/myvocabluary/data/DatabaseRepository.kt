package com.gazim.myvocabluary.data

import com.gazim.myvocabluary.app.model.Link
import com.gazim.myvocabluary.app.model.Word
import com.gazim.myvocabluary.app.model.WordWithLinks
import com.gazim.myvocabluary.data.room.dao.VocabularyDAO
import com.gazim.myvocabluary.data.room.model.LinkDB
import com.gazim.myvocabluary.data.room.model.WordDB

class DatabaseRepository(private val vocabularyDAO: VocabularyDAO) : IDatabaseRepository {

    override suspend fun getWords(): List<Word> = vocabularyDAO.getWords().map {
        Word(
            id = it.id,
            word = it.word,
            transcription = it.transcription,
            translation = it.translation,
        )
    }

    override suspend fun getLinks(wordId: Int): List<Link> = vocabularyDAO.getLinks(wordId).map {
        Link(
            id = it.id,
            link = it.link,
            wordId = it.wordId,
        )
    }

    override suspend fun insertWord(word: Word) = vocabularyDAO.insertWord(
        WordDB(
            id = word.id,
            word = word.word,
            transcription = word.transcription,
            translation = word.translation,
        ),
    ).let { word.copy(id = it.toInt()) }

    override suspend fun insertLink(link: Link) = vocabularyDAO.insertLink(
        LinkDB(
            id = link.id,
            link = link.link,
            wordId = link.wordId,
        ),
    ).let { link.copy(id = it.toInt()) }

    override suspend fun getWordWithLinks(wordId: Int): WordWithLinks =
        vocabularyDAO.getWordsWithLink(wordId).run {
            WordWithLinks(
                word = Word(
                    id = word.id,
                    word = word.word,
                    transcription = word.transcription,
                    translation = word.translation,
                ),
                links = links.map {
                    Link(
                        id = it.id,
                        link = it.link,
                        wordId = it.wordId,
                    )
                },
            )
        }

    override suspend fun insertLinks(links: List<Link>): List<Link> =
        vocabularyDAO.insertLinks(
            links.map {
                LinkDB(
                    id = it.id,
                    link = it.link,
                    wordId = it.wordId,
                )
            },
        ).zip(links) { i, l -> l.copy(id = i.toInt()) }
}
