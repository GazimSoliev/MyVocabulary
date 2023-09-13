package com.gazim.myvocabluary.data.parser

import com.gazim.myvocabluary.app.model.IWord

interface IWordsParser {
    fun parse(text: String): List<IWord>
}
