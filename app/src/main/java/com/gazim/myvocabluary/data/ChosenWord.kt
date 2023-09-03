package com.gazim.myvocabluary.data

class ChosenWord : IChosenWord {
    var wordId: Int = 0
    override suspend fun setWord(wordId: Int) {
        this.wordId = wordId
    }

    override suspend fun getWordId(): Int {
        return wordId
    }
}
