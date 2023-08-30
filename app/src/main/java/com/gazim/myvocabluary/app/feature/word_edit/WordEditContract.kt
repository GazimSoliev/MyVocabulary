package com.gazim.myvocabluary.app.feature.word_edit

import androidx.compose.ui.text.input.TextFieldValue
import com.gazim.myvocabluary.app.common.IAction
import com.gazim.myvocabluary.app.common.ISideEffect
import com.gazim.myvocabluary.app.common.IState

data class WordEditState(
    val word: TextFieldValue = TextFieldValue(),
    val transcription: TextFieldValue = TextFieldValue(),
    val translation: TextFieldValue = TextFieldValue(),
    val links: List<TextFieldValue> = listOf()
) : IState

sealed interface WordEditSideEffect : ISideEffect {

}

sealed interface WordEditAction : IAction {
    data class EditWord(val word: TextFieldValue) : WordEditAction
    data class EditTranscription(val transcription: TextFieldValue) : WordEditAction
    data class EditTranslation(val translation: TextFieldValue) : WordEditAction
    data class EditLinks(val links: List<TextFieldValue>) : WordEditAction
    data object AddLink : WordEditAction
}