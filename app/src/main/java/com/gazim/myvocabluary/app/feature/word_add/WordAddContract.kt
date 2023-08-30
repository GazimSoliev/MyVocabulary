package com.gazim.myvocabluary.app.feature.word_add

import androidx.compose.ui.text.input.TextFieldValue
import com.gazim.myvocabluary.app.common.IAction
import com.gazim.myvocabluary.app.common.ISideEffect
import com.gazim.myvocabluary.app.common.IState

data class WordAddState(
    val word: TextFieldValue = TextFieldValue(),
    val transcription: TextFieldValue = TextFieldValue(),
    val translation: TextFieldValue = TextFieldValue(),
    val links: List<TextFieldValue> = listOf()
): IState

sealed interface WordAddSideEffect: ISideEffect {
    data object Back: WordAddSideEffect
}

sealed interface WordAddAction: IAction {
    data class EditWord(val word: TextFieldValue) : WordAddAction
    data class EditTranscription(val transcription: TextFieldValue) : WordAddAction
    data class EditTranslation(val translation: TextFieldValue) : WordAddAction
    data class EditLink(val index: Int, val link: TextFieldValue) : WordAddAction
    data object AddLink : WordAddAction
    data object Save : WordAddAction
    data object Back : WordAddAction
}