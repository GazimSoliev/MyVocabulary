package com.gazim.myvocabluary.data

import com.gazim.myvocabluary.app.model.Word

interface IChosenWord {
    suspend fun setWord(word: Word)
    suspend fun getWord(): Word
}
