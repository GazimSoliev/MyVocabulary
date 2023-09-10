package com.gazim.myvocabluary.data

import com.gazim.myvocabluary.app.model.WordID

class TestWordRepository(private val databaseRepository: IDatabaseRepository) : ITestWordRepository {
    private var _wordIds: Iterator<Int>? = null
    private val wordIds: Iterator<Int> get() = requireNotNull(_wordIds) { "ITestWordRepository is not started" }
    override suspend fun start() {
        _wordIds = databaseRepository.getRandomWordIds().iterator()
    }

    override suspend fun hasNext(): Boolean = wordIds.hasNext()

    override suspend fun nextWord(): WordID = databaseRepository.getWordById(wordIds.next())

    override suspend fun finish() {
        _wordIds = null
    }
}
