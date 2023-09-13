package com.gazim.myvocabluary.data

class ChosenWordRepository : IChosenWordRepostitory {
    var wordId: Int = 0
    override suspend fun setWord(wordId: Int) {
        this.wordId = wordId
    }

    override suspend fun getWordId(): Int {
        return wordId
    }
}
