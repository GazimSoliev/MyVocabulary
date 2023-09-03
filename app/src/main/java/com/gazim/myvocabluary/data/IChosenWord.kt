package com.gazim.myvocabluary.data

interface IChosenWord {
    suspend fun setWord(wordId: Int)
    suspend fun getWordId(): Int
}
