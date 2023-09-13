package com.gazim.myvocabluary.data.parser

import com.gazim.myvocabluary.app.model.IWord
import com.gazim.myvocabluary.app.model.Word

class WordsParser : IWordsParser {
    private val template =
        Regex("""^((.*(?!-)\S)\s?-?\s?\[(.+)]\s?-?\s?\((.+)\)\s?-?\s?(.+)\s?)|((.*\S)\s?-\s?(.+)\s?)$""")

    override fun parse(text: String): List<IWord> =
        text.split('\n').filter(String::isNotBlank).mapNotNull(template::find).map {
            Word(
                word = it.groupValues[2] + it.groupValues[7],
                transcription = it.groupValues[3],
                translation = it.groupValues[5] + it.groupValues[8],
                links = it.groupValues[4].split(',').map(String::trim),
            )
        }
}
