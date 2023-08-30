package com.gazim.myvocabluary.data

import com.gazim.myvocabluary.app.model.Word

class ChosenWord : IChosenWord {
    var word: Word? = null
    override suspend fun setWord(word: Word) {
        this.word = word
    }

    override suspend fun getWord(): Word = word!!
}
