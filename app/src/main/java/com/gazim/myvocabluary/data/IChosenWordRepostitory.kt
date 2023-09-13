package com.gazim.myvocabluary.data

interface IChosenWordRepostitory {
    suspend fun setWord(wordId: Int)
    suspend fun getWordId(): Int
}
