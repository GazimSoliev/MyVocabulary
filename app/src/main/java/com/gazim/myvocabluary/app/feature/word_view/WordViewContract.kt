package com.gazim.myvocabluary.app.feature.word_view

import com.gazim.myvocabluary.app.common.IAction
import com.gazim.myvocabluary.app.common.ISideEffect
import com.gazim.myvocabluary.app.common.IState

data class WordViewState(
    val word: String = "",
    val transcription: String = "",
    val translation: String = "",
    val links: List<String> = emptyList(),
) : IState

sealed interface WordViewSideEffect : ISideEffect {
    data object Back : WordViewSideEffect
}

sealed interface WordViewAction : IAction {
    data object Back : WordViewAction
}
