package com.gazim.myvocabluary.app.feature.word_test

import com.gazim.myvocabluary.app.common.IAction
import com.gazim.myvocabluary.app.common.ISideEffect
import com.gazim.myvocabluary.app.common.IState

data class WordTestState(
    val word: String = "",
    val transcription: String = "",
    val translation: String = "",
    val hiddenTranscription: Boolean = true,
    val hiddenTranslation: Boolean = true,
) : IState

sealed interface WordTestSideEffect : ISideEffect {
    data object Back : WordTestSideEffect
}

sealed interface WordTestAction : IAction {
    data object Back : WordTestAction
    data object NextWord : WordTestAction
    data object ChangeTranscriptionVisibility : WordTestAction
    data object ChangeTranslationVisibility : WordTestAction
}
